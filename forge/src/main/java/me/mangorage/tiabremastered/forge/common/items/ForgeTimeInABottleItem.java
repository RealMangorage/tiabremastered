package me.mangorage.tiabremastered.forge.common.items;

import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.world.item.ItemStack;

public class ForgeTimeInABottleItem extends TimeInABottleItem {
    public ForgeTimeInABottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}
