package youyihj.zenrecipereloading.mixins.forestry;

import com.blamejared.mtlib.utils.BaseAddForestry;
import forestry.api.recipes.ICraftingProvider;
import forestry.api.recipes.IForestryRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = BaseAddForestry.class, remap = false)
public interface BaseAddForestryAccessor<R extends IForestryRecipe> {

    @Accessor
    ICraftingProvider<R> getProvider();

    @Accessor
    R getRecipe();

}
