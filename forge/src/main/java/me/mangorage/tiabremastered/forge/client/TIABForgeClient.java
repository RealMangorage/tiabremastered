package me.mangorage.tiabremastered.forge.client;

import com.mojang.blaze3d.platform.InputConstants;
import me.mangorage.tiabremastered.client.ClientTIAB;
import me.mangorage.tiabremastered.client.renderer.entity.AccelerationEntityRenderer;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TIABForgeClient {

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TiabRegistries.ACCELERATION_ENTITY.get(), AccelerationEntityRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void keyRegistration(RegisterKeyMappingsEvent event) {
        event.register(
                ClientTIAB.USE_TIAB_KEY.createAndGet((key, type,keyCode, category) -> {
                    return new KeyMapping(key, KeyConflictContext.IN_GAME, type, keyCode, category);
                })
        );
    }
}
