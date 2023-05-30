package me.mangorage.tiabremastered.common.core.registries;

import com.mojang.blaze3d.platform.InputConstants;
import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Optional;

public class ModItems {
    public static final ReferenceRegistryObject<TimeInABottleItem> TIME_IN_A_BOTTLE_ITEM =
            ReferenceRegistryObject.of(
                    new ResourceLocation(Constants.MODID, "timeinabottle"),
                    () -> new TimeInABottleItem(new Item.Properties().stacksTo(1).tab(TIAB.getTAB()))
            );
}
