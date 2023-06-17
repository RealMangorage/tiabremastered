package me.mangorage.tiabremastered.fabric.datagen;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.fabric.datagen.providers.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.models.ModelProvider;
import org.jetbrains.annotations.Nullable;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(new ModModelProvider(generator));
    }

    @Override
    public @Nullable String getEffectiveModId() {
        return Constants.MODID;
    }
}
