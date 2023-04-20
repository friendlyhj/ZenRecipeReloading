package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author youyihj
 */
@Mixin(value = MCRecipeManager.ActionRemoveRecipesNoIngredients.class, remap = false)
public interface ActionRemoveRecipesNoIngredientsAccessor {
    @Accessor
    List<Pair<IIngredient, Boolean>> getOutputs();
}
