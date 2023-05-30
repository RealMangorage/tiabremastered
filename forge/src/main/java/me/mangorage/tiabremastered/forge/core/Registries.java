package me.mangorage.tiabremastered.forge.core;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.items.TimeInABottleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    public static final RegistryObject<TimeInABottleItem> TIME_IN_A_BOTTLE_ITEM = ITEMS.register(
            ModItems.TIME_IN_A_BOTTLE_ITEM.getNameID(),
            () -> ModItems.TIME_IN_A_BOTTLE_ITEM.initiate()
    );

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
