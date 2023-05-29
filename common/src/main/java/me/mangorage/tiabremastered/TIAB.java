package me.mangorage.tiabremastered;

import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;

import java.util.Optional;
import java.util.function.Function;

public class TIAB {
    private static CreativeModeTab TAB;
    private static TIAB INSTANCE;

    public static void init(ModPlatform platform, Function<Player, Optional<ITIAB>> ITIABGetter) {
        if (INSTANCE == null)
            INSTANCE = new TIAB(platform, ITIABGetter);
        else
            throw new IllegalStateException("Already initiated TIAB");
    }

    public static void initRegistries() {
        ModItems.init();
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

    public static CreativeModeTab getTAB() {
        return TAB;
    }

    public static void buildTab(CreativeModeTab tab) {
        if (TIAB.TAB != null) return;
        TIAB.TAB = tab;
    }
}