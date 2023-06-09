package me.mangorage.tiabremastered.fabric.client;

import me.mangorage.tiabremastered.client.ClientTIAB;
import me.mangorage.tiabremastered.client.renderer.entity.AccelerationEntityRenderer;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TIABFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(TiabRegistries.ACCELERATION_ENTITY.get(), AccelerationEntityRenderer::new);
        KeyBindingHelper.registerKeyBinding(ClientTIAB.USE_TIAB_KEY.createAndGet(null));
    }
}
