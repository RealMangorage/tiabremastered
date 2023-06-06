package me.mangorage.tiabremastered.client;

import me.mangorage.tiabremastered.client.keymapping.ReferenceKeyMapping;
import me.mangorage.tiabremastered.common.core.Translatable;
import org.lwjgl.glfw.GLFW;

public class ClientTIAB {
    public static final ReferenceKeyMapping USE_TIAB_KEY =
            ReferenceKeyMapping.of(
                    Translatable.USE_TIAB_KEY.getKey(),
                    Translatable.CATEGORY.getKey(),
                    GLFW.GLFW_KEY_H
            );
}
