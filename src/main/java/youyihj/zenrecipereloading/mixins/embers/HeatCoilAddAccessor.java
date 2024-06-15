package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.HeatCoil;
import teamroots.embers.recipe.HeatCoilRecipe;

@Mixin(value = HeatCoil.Add.class, remap = false)
public interface HeatCoilAddAccessor {
    @Accessor
    HeatCoilRecipe getRecipe();
}
