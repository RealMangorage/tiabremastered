package me.mangorage.tiabremastered.client.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.apache.commons.lang3.function.TriFunction;

public class ReferenceKeyMapping {
    public static ReferenceKeyMapping of(String keyID, String category, int keyCode) {
        return new ReferenceKeyMapping(keyID, category, keyCode);
    }

    private final String keyID, category;
    private final int keyCode;
    private KeyMapping value;
    private ReferenceKeyMapping(String keyID, String category, int keyCode) {
        this.keyID = keyID;
        this.category = category;
        this.keyCode = keyCode;
    }

    public void create(KeyMappingFunction function) {
        if (this.value != null)
            throw new IllegalStateException("Already created KeyMapping!");
        this.value = function.apply(keyID, category, keyCode, InputConstants.Type.KEYSYM);
    }

    public KeyMapping get() {
        return value;
    }
}
