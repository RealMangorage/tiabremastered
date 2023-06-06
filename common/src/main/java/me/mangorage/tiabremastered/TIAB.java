package me.mangorage.tiabremastered;

import me.mangorage.tiabremastered.common.core.misc.ModPlatform;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.world.entity.player.Player;
import java.util.Optional;
import java.util.function.Function;

public class TIAB {
    private static TIAB INSTANCE;

    public static void init(ModPlatform platform, Function<Player, Optional<ITIAB>> ITIABGetter) {
        if (INSTANCE == null)
            INSTANCE = new TIAB(platform, ITIABGetter);
        else
            throw new IllegalStateException("Already initiated TIAB");
    }

    public static TIAB getInstance() {
        return INSTANCE;
    }

    private final ModPlatform platform;
    private final Function<Player, Optional<ITIAB>> ITIABGetter;

    private TIAB(ModPlatform platform, Function<Player, Optional<ITIAB>> ITIABGetter) {
        this.platform = platform;
        this.ITIABGetter = ITIABGetter;
    }

    public ModPlatform getPlatform() {
        return platform;
    }

    public Optional<ITIAB> getTIAB(Player player) {
        return ITIABGetter.apply(player);
    }
}