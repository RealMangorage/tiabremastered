package me.mangorage.tiabremastered.common.items;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Optional;

// Cheat Item basically
public class TimeCapsuleItem extends Item {
    private final int timeAmount;
    public TimeCapsuleItem(Properties properties, int timeAmount) {
        super(properties);
        this.timeAmount = timeAmount;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide) return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        ItemStack itemStack = player.getItemInHand(interactionHand);
        Optional<ITIAB> itiab = TIAB.getInstance().getTIAB(player);
        if (!itiab.isPresent())
            return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
        itiab.ifPresent((tiaba -> tiaba.setTimeLeft(tiaba.getTimeLeft() + timeAmount)));

        if (!player.getAbilities().instabuild)
            itemStack.shrink(1);

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(interactionHand), level.isClientSide);
    }
}
