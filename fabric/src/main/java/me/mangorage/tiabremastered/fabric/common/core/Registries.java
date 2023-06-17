package me.mangorage.tiabremastered.fabric.common.core;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.LazyReferenceObject;
import me.mangorage.tiabremastered.common.core.registries.LazyReferenceRegistry;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import me.mangorage.tiabremastered.fabric.common.items.FabricTimeInABottleItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Registries {
    public static final CreativeModeTab TAB = TiabRegistries.TIAB_TAB.initiate(
            () -> FabricItemGroupBuilder.create(
                    new ResourceLocation(Constants.MODID, "main"))
                    .icon(() -> TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance())
                    .appendItems(
                            (list) -> {
                                list.add(TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance());
                                list.add(TiabRegistries.TIME_CAPSULE_ITEM.get().getDefaultInstance());
                            })
                    .build()
    );

    public static final FabricTimeInABottleItem TIME_IN_A_BOTTLE_ITEM =
            TiabRegistries.ITEMS.initiate(
                    TiabRegistries.TIME_IN_A_BOTTLE_ITEM,
                    () -> new FabricTimeInABottleItem(
                            new Item.Properties()
                                    .stacksTo(1)
                                    .tab(TiabRegistries.TIAB_TAB.get())
                    )
            );

    private static void register(LazyReferenceRegistry registry, LazyReferenceObject... objects) {
        for (LazyReferenceObject object : objects) {
            if (!object.hasDefault() && object.get() == null) throw new IllegalStateException("Unable to register object due to it not being initiated properly!");
            Registry.register(
                    registry.getRegistry(),
                    object.getID(),
                    object.get() == null ? registry.initiate(object) : object.get()
            );
        }
    }


    public static void init() {
        register(
                TiabRegistries.ITEMS,
                TiabRegistries.TIME_IN_A_BOTTLE_ITEM,
                TiabRegistries.TIME_CAPSULE_ITEM
        );

        register(
                TiabRegistries.BLOCKS,
                TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK
        );

        register(
                TiabRegistries.BLOCK_ENTITY_TYPES,
                TiabRegistries.TIME_COLLECTOR_MACHINE_BLOCK_ENTITY
        );

        register(
                TiabRegistries.ENTITY_TYPES,
                TiabRegistries.ACCELERATION_ENTITY
        );

    }
}
