package me.mangorage.tiabremastered.forge;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.forge.core.ModCapabilities;
import me.mangorage.tiabremastered.forge.core.TiabProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Optional;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TIABForge {

    public TIABForge() {
        TIAB.buildTab(new CreativeModeTab("tab.tiab") {
            @Override
            public ItemStack makeIcon() {
                return ModItems.TIME_IN_A_BOTTLE_ITEM.getDefaultInstance();
            }
        });

        TIAB.init(ModPlatform.FORGE, (player) -> {
            LazyOptional<ITIAB> lazy = player.getCapability(ModCapabilities.TIAB);
            if (lazy.isPresent())
                return lazy.resolve();

            return Optional.empty();
        });

        IEventBus FORGE = Bindings.getForgeBus().get();
        FORGE.addListener(this::tickEvent);
        FORGE.addGenericListener(Entity.class, this::attachCaps);
    }

    @SubscribeEvent
    public static void onCapabilityRegister(RegisterCapabilitiesEvent event) {
        event.register(ITIAB.class);
    }

    @SubscribeEvent
    public static void onRegister(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, new ResourceLocation(Constants.MODID, "timeinabottle"), () -> ModItems.TIME_IN_A_BOTTLE_ITEM);
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