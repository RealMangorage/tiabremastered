package me.mangorage.tiabremastered.fabric.common.items;

import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FabricTimeInABottleItem extends TimeInABottleItem {
    public FabricTimeInABottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
