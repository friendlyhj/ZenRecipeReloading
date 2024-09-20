package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.util.IngredientMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = IngredientMap.IngredientMapEntry.class, remap = false)
public class IngredientMapEntryMixin<T> {
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
        if (!(obj instanceof IngredientMap.IngredientMapEntry<?> other)) return false;

        //noinspection DataFlowIssue
        return this.ingredient.equals(((IngredientMapEntryMixin<?>)(Object)other).ingredient) &&
               this.entry.equals(((IngredientMapEntryMixin<?>)(Object)other).entry);
    }
}
