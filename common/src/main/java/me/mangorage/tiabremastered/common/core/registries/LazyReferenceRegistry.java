package me.mangorage.tiabremastered.common.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Supplier;

public class LazyReferenceRegistry<X> {
    public static <X> LazyReferenceRegistry<X> create(String modID, Registry<X> registry) {
        return new LazyReferenceRegistry<>(modID, registry);
    }

    private final String MOD_ID;
    private final Registry<X> REGISTRY;
    private final HashMap<String, LazyReferenceObject<? extends X>> REGISTRY_OBJECTS = new HashMap<>();
    private LazyReferenceRegistry(String modID, Registry<X> registry) {
        this.MOD_ID = modID;
        this.REGISTRY = registry;
    }

    public Registry<X> getRegistry() {
        return REGISTRY;
    }

    public <T extends X> T initiate(LazyReferenceObject<T> object) {
        if (object.get() != null)
            throw new IllegalStateException("Already initialized: " + object.getID());
        if (!object.hasDefault())
            throw new IllegalStateException("Must provide a Supplier for this registryObject! It doesnt contain any default supplier!");
        return object.initiate();
    }

    public <T extends X, Y extends T> Y initiate(LazyReferenceObject<T> object, Supplier<Y> overrideSupplier) {
        if (!object.canBeOverriden())
            throw new IllegalStateException("Cannot override this registryReference!");
        if (object.get() != null)
            throw new IllegalStateException("Already initialized: " + object.getID());

        return object.initiate(overrideSupplier);
    }

    protected <T extends X> LazyReferenceObject<T> prepareRegister(String ID, @Nullable Supplier<T> supplier, boolean canBeOverriden) {
        if (REGISTRY_OBJECTS.containsKey(ID))
            throw new IllegalStateException("Already registered an Object with the ID: " + ID + " using registry: " + REGISTRY.key().registry());
        LazyReferenceObject<T> lazyReferenceObject = new LazyReferenceObject<>(new ResourceLocation(MOD_ID, ID), supplier);
        lazyReferenceObject.setCanOverride(canBeOverriden);
        REGISTRY_OBJECTS.put(ID, lazyReferenceObject);
        return lazyReferenceObject;
    }

    protected <T extends X> LazyReferenceObject<T> prepareRegister(String ID, @Nullable Supplier<T> supplier) {
        return prepareRegister(ID, supplier, true);
    }
}
