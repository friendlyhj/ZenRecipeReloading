package youyihj.zenrecipereloading.mixins.forestry;

import com.blamejared.mtlib.utils.BaseRemoveForestry;
import forestry.api.recipes.ICraftingProvider;
import forestry.api.recipes.IForestryRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = BaseRemoveForestry.class, remap = false)
public interface BaseRemoveForestryAccessor<R extends IForestryRecipe> {
    @Accessor
    ICraftingProvider<R> getProvider();
}
