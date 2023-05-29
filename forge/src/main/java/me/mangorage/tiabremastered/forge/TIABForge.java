package me.mangorage.tiabremastered.forge;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.forge.capabilities.ITiabProvider;
import me.mangorage.tiabremastered.forge.capabilities.ModCapabilities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Optional;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TIABForge {
    public TIABForge() {
        TIAB.init(ModPlatform.FORGE, TIABForge::getTIAB);
    }

    @SubscribeEvent
    public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {
        event.register(ITIAB.class);
    }

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, new ResourceLocation(Constants.MODID, "timeinabottle"), () -> ModItems.TIME_IN_A_BOTTLE_ITEM);
    }

    public static Optional<ITIAB> getTIAB(Player player) {
        LazyOptional<ITIAB> lazy = player.getCapability(ModCapabilities.TIAB);
        if (lazy.isPresent())
            return lazy.resolve();

        return Optional.empty();
    }
}