package me.mangorage.tiabremastered.common.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.function.Supplier;

public class LazyReferenceRegistry<X> {
    public static <X> LazyReferenceRegistry<X> create(String modID, ResourceKey<Registry<X>> key) {
        return new LazyReferenceRegistry<>(modID, key);
    }

    private final String MOD_ID;
    private final ResourceKey<Registry<X>> RESOURCE_KEY;
    private final HashMap<String, LazyReferenceObject<? extends X>> REGISTRY_OBJECTS = new HashMap<>();
    private LazyReferenceRegistry(String modID, ResourceKey<Registry<X>> resourceKey) {
        this.MOD_ID = modID;
        this.RESOURCE_KEY = resourceKey;
    }

    public <T extends X> T initiate(String ID) {
        if (REGISTRY_OBJECTS.containsKey(ID))
            return initiate((LazyReferenceObject<T>) REGISTRY_OBJECTS.get(ID));

        throw new IllegalStateException("Registry Object: " + new ResourceLocation(MOD_ID, ID) + " does not exist!");
    }

    public <T extends X> T initiate(LazyReferenceObject<T> object) {
        if (object.get() != null)
            throw new IllegalStateException("Already initialized: " + object.getID());
        if (!object.hasDefault())
            throw new IllegalStateException("Must provide a Supplier for this registryObject! It doesnt contain any default supplier!");
        return object.initiate();
    }

    public <T extends X, Y extends T> Y initiate(String ID, Supplier<Y> overrideSupplier) {
        if (REGISTRY_OBJECTS.containsKey(ID))
            return initiate((LazyReferenceObject<T>) REGISTRY_OBJECTS.get(ID), overrideSupplier);

        throw new IllegalStateException("Registry Object: " + new ResourceLocation(MOD_ID, ID) + " does not exist!");
    }

    public <T extends X, Y extends T> Y initiate(LazyReferenceObject<T> object, Supplier<Y> overrideSupplier) {
        if (!object.canBeOverriden())
            throw new IllegalStateException("Cannot override this registryReference!");
        if (object.get() != null)
            throw new IllegalStateException("Already initialized: " + object.getID());

        return object.initiate(overrideSupplier);
    }

    public <T extends X> LazyReferenceObject<T> prepareRegister(String ID, @Nullable Supplier<T> supplier, boolean canBeOverriden) {
        if (REGISTRY_OBJECTS.containsKey(ID))
            throw new IllegalStateException("Already registered an Object with the ID: " + ID + " using registry: " + RESOURCE_KEY.registry());
        LazyReferenceObject<T> lazyReferenceObject = new LazyReferenceObject<>(new ResourceLocation(MOD_ID, ID), supplier);
        lazyReferenceObject.setCanOverride(canBeOverriden);
        REGISTRY_OBJECTS.put(ID, lazyReferenceObject);
        return lazyReferenceObject;
    }

    public <T extends X> LazyReferenceObject<T> prepareRegister(String ID, @Nullable Supplier<T> supplier) {
        return prepareRegister(ID, supplier, true);
    }
}
