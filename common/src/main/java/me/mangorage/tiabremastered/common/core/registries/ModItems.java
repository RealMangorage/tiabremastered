package me.mangorage.tiabremastered.common.core.registries;

import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.world.item.Item;

import static me.mangorage.tiabremastered.TIAB.TAB;

public class ModItems {
    public static final TimeInABottleItem TIME_IN_A_BOTTLE_ITEM = new TimeInABottleItem(new Item.Properties().stacksTo(1).tab(TAB));

    public static void init() {}
}
