package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.recipes.MCRecipeManager;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import youyihj.zenrecipereloading.compat.vanilla.CraftingRecipeCallbacks;
import youyihj.zenrecipereloading.compat.vanilla.ActionBaseRemoveRecipesAccessor;

import java.util.List;

/**
 * @author youyihj
 */
@Mixin(value = MCRecipeManager.ActionBaseRemoveRecipes.class, remap = false)
public abstract class ActionBaseRemoveRecipesMixin implements ActionBaseRemoveRecipesAccessor {
    @Unique
    private final Int2ObjectArrayMap<IRecipe> zenRecipeReload$removed = new Int2ObjectArrayMap<>();

    @Inject(method = "removeRecipes", at = @At("HEAD"))
    private void recordRemoveRecipes(List<ResourceLocation> removingRecipes, CallbackInfo ci) {
        zenRecipeReload$removed.clear();
        removingRecipes.forEach(it -> {
            IRecipe recipe = CraftingRecipeCallbacks.getRecipeRegistry().getValue(it);
            int id = CraftingRecipeCallbacks.getRecipeRegistry().getID(it);
            zenRecipeReload$removed.put(id, recipe);
        });
    }

    @Override
    public Int2ObjectArrayMap<IRecipe> zenRecipeReload$getRemovedRecipes() {
        return zenRecipeReload$removed;
    }
}
