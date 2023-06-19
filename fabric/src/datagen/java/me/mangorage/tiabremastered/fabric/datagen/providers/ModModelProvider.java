package me.mangorage.tiabremastered.fabric.datagen.providers;

import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;

import java.util.Locale;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // Do nothing
    }

    @Override
    public void generateItemModels(ItemModelGenerators model) {
        model.generateFlatItem(TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }

}
