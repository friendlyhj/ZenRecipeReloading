package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.recipes.MCRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = MCRecipeManager.ActionBaseAddRecipe.class, remap = false)
public interface ActionBaseAddRecipeAccessor {
    @Accessor
    String getName();
}
