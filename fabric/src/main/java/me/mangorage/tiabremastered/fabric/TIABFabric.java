package me.mangorage.tiabremastered.fabric;

import me.mangorage.tiabremastered.TIAB;
import me.mangorage.tiabremastered.common.core.ModPlatform;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;

public class TIABFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TIAB.init(ModPlatform.FABRIC, (Player) -> {
            return null;
        });
    }
}