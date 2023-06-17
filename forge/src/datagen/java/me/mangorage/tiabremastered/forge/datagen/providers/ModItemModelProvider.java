package me.mangorage.tiabremastered.forge.datagen.providers;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.common.core.registries.TiabRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(TiabRegistries.TIME_IN_A_BOTTLE_ITEM.get());
    }
}
