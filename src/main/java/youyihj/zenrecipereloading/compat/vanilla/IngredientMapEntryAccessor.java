package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.api.item.IIngredient;

/**
 * @author kurrycat
 */
public interface IngredientMapEntryAccessor<T> {
    IIngredient zenRecipeReload$getIngredient();

    T zenRecipeReload$getEntry();
}
