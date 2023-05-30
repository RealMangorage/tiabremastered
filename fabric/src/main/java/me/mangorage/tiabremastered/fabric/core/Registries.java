package me.mangorage.tiabremastered.fabric.core;

import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.core.Registry;

public class Registries {
    public static final TimeInABottleItem TIME_IN_A_BOTTLE_ITEM = ModItems.TIME_IN_A_BOTTLE_ITEM.initiate();


    public static void init() {
        Registry.register(Registry.ITEM, ModItems.TIME_IN_A_BOTTLE_ITEM.getID(), TIME_IN_A_BOTTLE_ITEM);
    }
}
