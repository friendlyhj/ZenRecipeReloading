package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.util.IngredientMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import youyihj.zenrecipereloading.compat.vanilla.IngredientMapEntryAccessor;

@Mixin(value = IngredientMap.IngredientMapEntry.class, remap = false)
public class IngredientMapEntryMixin<T> implements IngredientMapEntryAccessor<T> {
    @Shadow
    @Final
    private IIngredient ingredient;

    @Shadow
    @Final
    private T entry;

    // We need this.
    // I don't know how IngredientMap.unregister is even supposed to work without this, but I suppose it wasn't implemented completely
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IngredientMap.IngredientMapEntry<?>)) return false;

        IngredientMap.IngredientMapEntry<?> other = (IngredientMap.IngredientMapEntry<?>) obj;

        // noinspection unchecked
        IngredientMapEntryAccessor<T> otherAccessor = (IngredientMapEntryAccessor<T>) other;
        return this.ingredient.equals(otherAccessor.zenRecipeReload$getIngredient()) &&
               this.entry.equals(otherAccessor.zenRecipeReload$getEntry());
    }

    @Override
    public IIngredient zenRecipeReload$getIngredient() {
        return ingredient;
    }

    @Override
    public T zenRecipeReload$getEntry() {
        return entry;
    }
}
