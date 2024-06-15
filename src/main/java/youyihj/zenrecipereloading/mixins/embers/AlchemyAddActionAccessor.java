package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Alchemy;
import teamroots.embers.recipe.AlchemyRecipe;

/**
 * @author youyihj
 */
@Mixin(value = Alchemy.Add.class, remap = false)
public interface AlchemyAddActionAccessor {
    @Accessor
    AlchemyRecipe getRecipe();
}
