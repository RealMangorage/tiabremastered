package me.mangorage.tiabremastered.client;

import me.mangorage.tiabremastered.client.keymapping.ReferenceKeyMapping;
import org.lwjgl.glfw.GLFW;

public class ClientTIAB {
    public static final String CATEGORY = "key.categories.tiabremastered";
    public static final ReferenceKeyMapping USE_TIAB_KEY = ReferenceKeyMapping.of("key.tiabremastered.usetiab", CATEGORY, GLFW.GLFW_KEY_H);
}
