package me.mangorage.tiabremastered.client.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.apache.commons.lang3.function.TriFunction;
import org.apache.logging.log4j.util.TriConsumer;

@FunctionalInterface
public interface KeyMappingFunction {
    KeyMapping apply(String key, InputConstants.Type type, int keyCode, String category);
}
