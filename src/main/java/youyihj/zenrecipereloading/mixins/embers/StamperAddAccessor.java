package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Stamper;
import teamroots.embers.recipe.ItemStampingRecipe;

@Mixin(value = Stamper.Add.class, remap = false)
public interface StamperAddAccessor {
    @Accessor
    ItemStampingRecipe getRecipe();
}

