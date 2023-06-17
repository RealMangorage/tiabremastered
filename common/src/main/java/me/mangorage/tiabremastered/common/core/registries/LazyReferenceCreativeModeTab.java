package me.mangorage.tiabremastered.common.core.registries;

import net.minecraft.world.item.CreativeModeTab;
import java.util.function.Supplier;

public class LazyReferenceCreativeModeTab<T extends CreativeModeTab> implements Supplier<T> {
    public static <T extends CreativeModeTab> LazyReferenceCreativeModeTab<T> prepareDefaultRegister(Supplier<T> supplier) {
        return new LazyReferenceCreativeModeTab<>(supplier);
    }

    private final Supplier<T> supplier;
    private T value;

    private LazyReferenceCreativeModeTab(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T initiate(Supplier<T> supplier) {
        if (this.value != null)
            throw new IllegalStateException("Cannot intiate CreativeModeTabReference as its already been initiated!");
        this.value = supplier.get();
        return value;
    }

    public T initiate() {
        if (supplier == null)
            throw new IllegalStateException("Cannot initiate default Supplier as default is null");

        return initiate(this.supplier);
    }

    public T get() {
        return this.value;
    }
}
