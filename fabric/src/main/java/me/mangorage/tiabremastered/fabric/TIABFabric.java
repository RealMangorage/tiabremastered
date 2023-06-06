package me.mangorage.tiabremastered.fabric;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.misc.ModPlatform;
import me.mangorage.tiabremastered.common.core.Util;
import me.mangorage.tiabremastered.fabric.common.core.Registries;
import me.mangorage.tiabremastered.fabric.common.core.TiabProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Optional;

public class TIABFabric implements ModInitializer {
    @Override
    @SuppressWarnings({"Deprecated", "ScheduledForRemoval"})
    public void onInitialize() {

        TIAB.init(ModPlatform.FABRIC, (Player) -> {
            return (Optional) TiabProvider.TIAB.maybeGet(Player);
        });

        Registries.init();

        ServerTickEvents.START_SERVER_TICK.register((server -> {
            server.getPlayerList().getPlayers().forEach(player -> {
                TiabProvider.TIAB.maybeGet(player).ifPresent(fabricTiab -> fabricTiab.tick(player));
            });
        }));

        ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
            if (!alive)
                TiabProvider.TIAB.maybeGet(oldPlayer).ifPresent(originalTiab -> {
                    TiabProvider.TIAB.maybeGet(newPlayer).ifPresent(newTiab -> {
                       Util.updateNewTiab(originalTiab, newTiab);
                    });
                });
        }));

    }
}