package me.mangorage.tiabremastered.client.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.Nullable;

public class ReferenceKeyMapping {
    public static ReferenceKeyMapping of(String keyID, String category, int keyCode) {
        return new ReferenceKeyMapping(keyID, category, keyCode);
    }

    private static KeyMappingFunction DEFAULT_FUNCTION = (keyID, type, keyCode, category) -> new KeyMapping(keyID, type, keyCode, category);

    private final String keyID, category;
    private final int keyCode;
    private KeyMapping value;

    private ReferenceKeyMapping(String keyID, String category, int keyCode) {
        this.keyID = keyID;
        this.category = category;
        this.keyCode = keyCode;
    }

    public void create(@Nullable KeyMappingFunction function) {
        if (this.value != null)
            throw new IllegalStateException("Already created KeyMapping!");
        if (function == null)
            function = DEFAULT_FUNCTION;

        this.value = function.apply(keyID, InputConstants.Type.KEYSYM, keyCode, category);
    }

    public KeyMapping createAndGet(KeyMappingFunction function) {
        create(function);
        return this.value;
    }

    public KeyMapping get() {
        return value;
    }
}
