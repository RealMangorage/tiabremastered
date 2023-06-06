package me.mangorage.tiabremastered.fabric.common.core;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import me.mangorage.tiabremastered.common.core.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class TiabProvider implements EntityComponentInitializer {

    public static final ComponentKey<FabricTiab> TIAB =
            ComponentRegistry.getOrCreate(new ResourceLocation(Constants.MODID, "tiab"), FabricTiab.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(TIAB, (player -> {return new FabricTiab();}));
        CreativeModeTab tab;
    }
}
