package me.mangorage.tiabremastered.forge.capabilities;

import me.mangorage.tiabremastered.common.core.tiab.ITIAB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities {
    public static final Capability<ITIAB> TIAB = CapabilityManager.get(new CapabilityToken<>(){});
}
