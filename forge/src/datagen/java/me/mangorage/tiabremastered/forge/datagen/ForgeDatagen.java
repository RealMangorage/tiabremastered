package me.mangorage.tiabremastered.forge.datagen;

import me.mangorage.tiabremastered.common.core.Constants;
import me.mangorage.tiabremastered.forge.datagen.providers.ModItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeDatagen {

    static {
        boolean loaded = true;

        Logger.getLogger(Constants.MODID).info("WOOOP!");
    }
    @SubscribeEvent
    public static void registerDataGen(GatherDataEvent event) {

        var EFH = event.getExistingFileHelper();
        var DG = event.getGenerator();
        var MC = event.getModContainer();

        var client = event.includeClient();
        var server = event.includeServer();
        var dev = event.includeDev();
        var reports = event.includeReports();

        DG.addProvider(client, new ModItemModelProvider(DG, EFH));

    }
}
