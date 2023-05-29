package me.mangorage.tiabremastered.forge;

import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import me.mangorage.tiabremastered.forge.capabilities.ModCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class Util {
    public static Optional<ITIAB> getTIAB(Player player) {
        LazyOptional<ITIAB> lazy = player.getCapability(ModCapabilities.TIAB);
        if (lazy.isPresent())
            return lazy.resolve();

        return Optional.empty();
    }
}
