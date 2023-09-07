package youyihj.zenrecipereloading.compat.vanilla;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.item.crafting.IRecipe;

/**
 * @author youyihj
 */
public interface ActionBaseRemoveRecipesAccessor {
    Int2ObjectArrayMap<IRecipe> zenRecipeReload$getRemovedRecipes();
}
