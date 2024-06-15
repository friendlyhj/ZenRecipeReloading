package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Melter;
import teamroots.embers.recipe.ItemMeltingRecipe;

@Mixin(value = Melter.Add.class, remap = false)
public interface MelterAddAccessor {
    @Accessor
    ItemMeltingRecipe getRecipe();
}

