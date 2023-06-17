package me.mangorage.tiabremastered.forge;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.Util;
import me.mangorage.tiabremastered.common.core.tiab.BasicTiab;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.core.misc.ModPlatform;
import me.mangorage.tiabremastered.common.commands.TiabCommands;
import me.mangorage.tiabremastered.forge.common.core.ModCapabilities;
import me.mangorage.tiabremastered.forge.common.core.Registries;
import me.mangorage.tiabremastered.forge.common.core.TiabProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;
import java.util.function.Consumer;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TIABForge {

    public TIABForge() {

        TIAB.init(ModPlatform.FORGE, (player) -> {
            LazyOptional<ITIAB> lazy = player.getCapability(ModCapabilities.TIAB);
            if (lazy.isPresent())
                return lazy.resolve();

            return Optional.empty();
        });

        Registries.init();

        IEventBus FORGE = Bindings.getForgeBus().get();
        FORGE.addListener(this::tickEvent);
        FORGE.addListener(this::registerCommands);
        FORGE.addListener(this::onPlayerRespawn);
        FORGE.addGenericListener(Entity.class, this::attachCaps);
    }

    @SubscribeEvent
    public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {
        event.register(ITIAB.class);
    }


    public void onPlayerRespawn(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        Player originalPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        originalPlayer.reviveCaps();
        originalPlayer.getCapability(ModCapabilities.TIAB).ifPresent(oldTiab -> {
            newPlayer.getCapability(ModCapabilities.TIAB).ifPresent(newTiab -> {
                Util.updateNewTiab(oldTiab, newTiab);
            });
        });
        originalPlayer.invalidateCaps();
    }

    public void registerCommands(RegisterCommandsEvent event) {
        TiabCommands.register(event.getDispatcher());
    }

    public void tickEvent(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer())
            event.player.getCapability(ModCapabilities.TIAB).ifPresent(i -> i.tick(event.player));
    }

    public void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getLevel().isClientSide)
                event.addCapability(
                        new ResourceLocation(Constants.MODID, "tiab"),
                        new TiabProvider(new BasicTiab())
                );
        }
    }
}