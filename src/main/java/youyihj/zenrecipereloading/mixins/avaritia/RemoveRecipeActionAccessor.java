package youyihj.zenrecipereloading.mixins.avaritia;

import morph.avaritia.compat.crafttweaker.RemoveRecipeAction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author youyihj
 */
@Mixin(value = RemoveRecipeAction.class, remap = false)
public interface RemoveRecipeActionAccessor<R extends IForgeRegistryEntry<R>> {
    @Accessor
    Map<ResourceLocation, R> getRegistry();

    @Accessor
    Function<Collection<R>, List<ResourceLocation>> getFilter();
}
