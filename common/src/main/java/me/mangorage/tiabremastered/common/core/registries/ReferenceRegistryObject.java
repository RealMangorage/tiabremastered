package me.mangorage.tiabremastered.common.core.registries;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ReferenceRegistryObject<T> {
    public static <T> ReferenceRegistryObject<T> of(ResourceLocation ID, Supplier<T> supplier) {
        return new ReferenceRegistryObject<>(ID, supplier);
    }

    private final Supplier<T> objectSupplier;
    private final ResourceLocation ID;
    private T value;
    private ReferenceRegistryObject(ResourceLocation ID, Supplier<T> supplier) {
        this.ID = ID;
        this.objectSupplier = supplier;
    }
    public T initiate() {
        if (this.value != null)
            throw new IllegalStateException("Already registered object!");

        this.value = objectSupplier.get();
        return this.value;
    }

    public T get() {
        return value;
    }

    public String getModID() {
        return ID.getNamespace();
    }

    public String getNameID() {
        return ID.getPath();
    }

    public ResourceLocation getID() {
        return ID;
    }
}
