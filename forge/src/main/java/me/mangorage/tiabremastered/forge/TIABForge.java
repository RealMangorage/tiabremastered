package me.mangorage.tiabremastered.forge;

import com.mojang.blaze3d.platform.InputConstants;
import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.client.ClientTIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.common.core.tiab.TiabCommands;
import me.mangorage.tiabremastered.forge.core.ModCapabilities;
import me.mangorage.tiabremastered.forge.core.Registries;
import me.mangorage.tiabremastered.forge.core.TiabProvider;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TIABForge {

    public TIABForge() {
        TIAB.buildTab(new CreativeModeTab("tab.tiab") {
            @Override
            public ItemStack makeIcon() {
                return ModItems.TIME_IN_A_BOTTLE_ITEM.get().getDefaultInstance();
            }
        });

        TIAB.init(ModPlatform.FORGE, (player) -> {
            LazyOptional<ITIAB> lazy = player.getCapability(ModCapabilities.TIAB);
            if (lazy.isPresent())
                return lazy.resolve();

            return Optional.empty();
        });

        Registries.init(FMLJavaModLoadingContext.get().getModEventBus());

        IEventBus FORGE = Bindings.getForgeBus().get();
        FORGE.addListener(this::tickEvent);
        FORGE.addListener(this::registerCommands);
        FORGE.addGenericListener(Entity.class, this::attachCaps);
    }

    @SubscribeEvent
    public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {
        event.register(ITIAB.class);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void keyRegistration(RegisterKeyMappingsEvent event) {
        ClientTIAB.USE_TIAB_KEY.create((key, category, keycode, type) -> {
            return new KeyMapping(key, KeyConflictContext.IN_GAME, type, keycode, category);
        });

        event.register(ClientTIAB.USE_TIAB_KEY.get());
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
                event.addCapability(new ResourceLocation(Constants.MODID, "tiab"), new TiabProvider());
        }
    }
}