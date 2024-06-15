package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.DawnstoneAnvil;
import teamroots.embers.recipe.DawnstoneAnvilRecipe;

/**
 * @author youyihj
 */
@Mixin(value = DawnstoneAnvil.Add.class, remap = false)
public interface DawnstoneAnvilAddAccessor {
    @Accessor
    DawnstoneAnvilRecipe getRecipe();
}
