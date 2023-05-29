package me.mangorage.tiabremastered;

import me.mangorage.tiabremastered.common.core.registries.ModItems;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static me.mangorage.tiabremastered.common.core.registries.ModItems.TIME_IN_A_BOTTLE_ITEM;

public class TIAB {
    public static final CreativeModeTab TAB = new CreativeModeTab(CreativeModeTab.TABS.length, "tab.tiab") {
        @Override
        public ItemStack makeIcon() {
            return TIME_IN_A_BOTTLE_ITEM.getDefaultInstance();
        }
    };


    public static TIAB INSTANCE;

    public static void init(ModPlatform platform, Function<Player, Optional<ITIAB>> ITIABGetter) {
        if (INSTANCE == null)
            INSTANCE = new TIAB(platform, ITIABGetter);
        else
            throw new IllegalStateException("Already initiated TIAB");
    }

    public static void initRegistries() {
        ModItems.init();
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