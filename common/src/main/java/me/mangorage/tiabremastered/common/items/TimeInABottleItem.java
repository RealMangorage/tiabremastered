package me.mangorage.tiabremastered.common.items;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Util;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TimeInABottleItem extends Item {
    public TimeInABottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        if (level.isClientSide) return InteractionResult.PASS;
        BlockPos pos = useOnContext.getClickedPos();

        if (!Util.doesBlockTick(level, pos)) return InteractionResult.FAIL;
        Player player = useOnContext.getPlayer();
        Optional<ITIAB> itiabOptional = TIAB.getInstance().getTIAB(player);
        if (itiabOptional.isEmpty()) return InteractionResult.FAIL;
        ITIAB itiab = itiabOptional.get();

        Optional<AccelerationEntity> AEO =
                level.getEntitiesOfClass(AccelerationEntity.class, new AABB(pos))
                        .stream()
                        .filter(AE -> {
                            return AE.getAcceleratedBlockPos().equals(pos);
                        }).findAny();
        AEO.ifPresentOrElse(entity -> {
            if ((entity.getTimeRate() * 2) > 256) return;
            Util.setTicksForEntity(player, entity, entity.getTimeRate() * 2, itiab);
        }, () -> {
            if (itiab.getTimeLeft() < Util.getTicksForRate(100, 2)) return;
            AccelerationEntity entity = new AccelerationEntity(level, pos);
            level.addFreshEntity(entity);
            Util.setTicksForEntity(player, entity, 2, itiab);
        });

        return super.useOn(useOnContext);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (level != null && !level.isClientSide && entity instanceof Player player) {
            Optional<ITIAB> optionalITIAB = TIAB.getInstance().getTIAB(player);
            optionalITIAB.ifPresent(itiab -> {
                itemStack.setTag(itiab.serialize(new CompoundTag()));
            });
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (level == null) return;

        if (level.isClientSide) {
            CompoundTag tag = itemStack.getOrCreateTag();
            int time = -1;
            int totaltime = -1;

            if (tag.contains("time"))
                time = tag.getInt("time");
            if (tag.contains("totaltime"))
                totaltime = tag.getInt("totaltime");

            if (time != -1) {
                list.add(Component.literal("Time left:"));
                list.add(Component.literal(Util.getFormattedTimeString(time)));
            }

            if (totaltime != -1) {
                list.add(Component.literal("Total Accumulated Time:"));
                list.add(Component.literal(Util.getFormattedTimeString(totaltime)));
            }
        }
    }
}
