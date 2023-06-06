package me.mangorage.tiabremastered.common.core;

import net.minecraft.network.chat.Component;

public class Translatable {
    private final String key;
    public Translatable(String key) {
        this.key = key;
    }

    public Component get(Object... objects) {
        if (objects == null)
            return Component.translatable(key);
        return Component.translatable(key, objects);
    }

    public String getKey() {
        return key;
    }


    public static Translatable
            CATEGORY = new Translatable("key.categories.tiabremastered"),
            USE_TIAB_KEY = new Translatable("key.tiabremastered.usetiab");

}
