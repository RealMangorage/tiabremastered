package me.mangorage.tiabremastered.forge;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.forge.capabilities.ITiabProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onCapabilityAttach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer)
            event.addCapability(new ResourceLocation(Constants.MODID, "tiabprovider"), new ITiabProvider());
    }

    @SubscribeEvent
    public static void onTickEvent(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            Optional<ITIAB> optionalITIAB = Util.getTIAB((ServerPlayer) event.player);
            optionalITIAB.ifPresent(itiab -> itiab.tick(event.player));
        }
    }
}
