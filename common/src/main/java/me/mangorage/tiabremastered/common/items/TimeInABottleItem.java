package me.mangorage.tiabremastered.common.items;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Util;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TimeInABottleItem extends Item {
    public TimeInABottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void verifyTagAfterLoad(CompoundTag compoundTag) {
        super.verifyTagAfterLoad(compoundTag);
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
