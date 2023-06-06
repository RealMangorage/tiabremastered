package me.mangorage.tiabremastered.forge.common.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class DeferredRegisteryUtils<T> {
    private final static List<DeferredRegister<?>> registries = new ArrayList<>();
    public static <T> DeferredRegister<T> register(DeferredRegister<T> register, IEventBus bus) {
        if (registries.contains(register))
            return register;
        register.register(bus);
        registries.add(register);
        return register;
    }

    public static <T> DeferredRegister<T> register(DeferredRegister<T> register) {
        return register(register, FMLJavaModLoadingContext.get().getModEventBus());
    }
}
