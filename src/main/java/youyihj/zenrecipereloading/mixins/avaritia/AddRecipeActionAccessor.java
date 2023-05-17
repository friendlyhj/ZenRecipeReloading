package youyihj.zenrecipereloading.mixins.avaritia;

import morph.avaritia.compat.crafttweaker.AddRecipeAction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

/**
 * @author youyihj
 */
@Mixin(value = AddRecipeAction.class, remap = false)
public interface AddRecipeActionAccessor<R extends IForgeRegistryEntry<R>> {
    @Accessor
    R getRecipe();

    @Accessor
    Map<ResourceLocation, R> getRegistry();
}
