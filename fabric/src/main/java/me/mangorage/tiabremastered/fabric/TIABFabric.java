package me.mangorage.tiabremastered.fabric;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.fabric.core.TiabProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class TIABFabric implements ModInitializer {

    @Override
    @SuppressWarnings({"Deprecated", "ScheduledForRemoval"})
    public void onInitialize() {
        TIAB.buildTab(
                FabricItemGroupBuilder.create(
                        new ResourceLocation(Constants.MODID, "tab"))
                        .icon(() -> ModItems.TIME_IN_A_BOTTLE_ITEM.getDefaultInstance())
                        .appendItems((list) -> list.add(ModItems.TIME_IN_A_BOTTLE_ITEM.getDefaultInstance()))
                        .build()
        );

        TIAB.init(ModPlatform.FABRIC, (Player) -> {
            return (Optional) TiabProvider.TIAB.maybeGet(Player);
        });

        ServerTickEvents.START_SERVER_TICK.register((server -> {
            server.getPlayerList().getPlayers().forEach(player -> {
                TiabProvider.TIAB.maybeGet(player).ifPresent(fabricTiab -> fabricTiab.tick(player));
            });
        }));

    }
}