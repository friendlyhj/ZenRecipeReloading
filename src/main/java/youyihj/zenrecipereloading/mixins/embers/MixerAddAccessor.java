package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Mixer;
import teamroots.embers.recipe.FluidMixingRecipe;

@Mixin(value = Mixer.Add.class, remap = false)
public interface MixerAddAccessor {
    @Accessor
    FluidMixingRecipe getRecipe();
}

