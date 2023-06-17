package me.mangorage.tiabremastered.common.core.registries;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LazyReferenceObject<T> implements Supplier<T> {
    @Nullable
    private final Supplier<T> objectSupplier;
    private final ResourceLocation ID;
    private boolean canOverride;
    private T value;

    protected LazyReferenceObject(ResourceLocation ID, @Nullable Supplier<T> supplier) {
        this.ID = ID;
        this.objectSupplier = supplier;
    }

    protected <X extends T> X initiate(Supplier<X> supplier) {
        this.value = supplier.get();
        return (X) this.value;
    }

    protected T initiate() {
        this.value = objectSupplier.get();
        return this.value;
    }

    public boolean hasDefault() {
        return objectSupplier == null ? false : true;
    }

    protected boolean canBeOverriden() {
        return canOverride;
    }

    protected void setCanOverride(boolean canOverride) {
        this.canOverride = canOverride;
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
