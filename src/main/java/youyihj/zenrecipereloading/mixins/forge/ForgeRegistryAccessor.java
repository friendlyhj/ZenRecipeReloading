package youyihj.zenrecipereloading.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @author youyihj
 */
@Mixin(value = ForgeRegistry.class, remap = false)
public interface ForgeRegistryAccessor<V extends IForgeRegistryEntry<V>> {
    @Accessor
    void setDummyFactory(IForgeRegistry.DummyFactory<V> dummyFactory);

    @Invoker
    boolean callMarkDummy(ResourceLocation key, int id);

    @Invoker
    int callAdd(int id, V value, String owner);
}
