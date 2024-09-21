package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.api.item.IIngredient;

public interface IngredientMapEntryAccessor<T> {
    IIngredient zenRecipeReload$getIngredient();

    T zenRecipeReload$getEntry();
}
