package me.mangorage.tiabremastered.common.core.registries;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final TimeInABottleItem TIME_IN_A_BOTTLE_ITEM = new TimeInABottleItem(new Item.Properties().stacksTo(1).tab(TIAB.getTAB()));

    public static void init() {
        if (TIAB.getInstance().getPlatform() == ModPlatform.FABRIC) {
            Registry.register(
                    Registry.ITEM,
                    new ResourceLocation(Constants.MODID, "timeinabottle"),
                    TIME_IN_A_BOTTLE_ITEM
            );
        }
    }
}
