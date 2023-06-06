package me.mangorage.tiabremastered.fabric.common.core;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.LazyReferenceObject;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import me.mangorage.tiabremastered.fabric.common.items.FabricTimeInABottleItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Registries {
    public static final CreativeModeTab TAB = TiabRegistries.TABS.initiate(
            TiabRegistries.TIAB_TAB,
            () -> FabricItemGroupBuilder.create(
                    new ResourceLocation(Constants.MODID, "main"))
                    .icon(() -> TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance())
                    .appendItems((list) -> list.add(TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance()))
                    .build()
    );

    public static final TimeInABottleItem TIME_IN_A_BOTTLE_ITEM =
            TiabRegistries.ITEMS.initiate(
                    TiabRegistries.TIME_IN_A_BOTTLE_ITEM,
                    () -> new FabricTimeInABottleItem(
                            new Item.Properties()
                                    .stacksTo(1)
                                    .tab(TiabRegistries.TIAB_TAB.get())
                    )
            );


    public static void init() {
        Registry.register(Registry.ITEM, TiabRegistries.TIME_IN_A_BOTTLE_ITEM.getID(), TIME_IN_A_BOTTLE_ITEM);
        Registry.register(Registry.ENTITY_TYPE, TiabRegistries.ACCELERATION_ENTITY.getID(), TiabRegistries.ENTITIES.initiate(TiabRegistries.ACCELERATION_ENTITY));
    }
}
