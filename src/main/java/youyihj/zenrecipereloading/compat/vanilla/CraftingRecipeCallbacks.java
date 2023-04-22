package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.mc1120.recipes.MCRecipeManager;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionBaseAddRecipeAccessor;
import youyihj.zenrecipereloading.mixins.forge.ForgeRegistryAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.Objects;

/**
 * @author youyihj
 */
public class CraftingRecipeCallbacks {
    @SuppressWarnings("unchecked")
    public static ForgeRegistryAccessor<IRecipe> getRecipeRegistryAccessor() {
        return ((ForgeRegistryAccessor<IRecipe>) ForgeRegistries.RECIPES);
    }

    public static ForgeRegistry<IRecipe> getRecipeRegistry() {
        return ((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES);
    }

    public static class RecipeAddition extends MixinAccessibleActionReloadCallback<MCRecipeManager.ActionBaseAddRecipe, ActionBaseAddRecipeAccessor> {

        public RecipeAddition(MCRecipeManager.ActionBaseAddRecipe action) {
            super(action);
        }

        @Override
        public void undo() {
            ResourceLocation rl = new ResourceLocation("crafttweaker", getActionAccessor().getName());
            int id = getRecipeRegistry().getID(rl);
            getRecipeRegistry().remove(rl);
            getRecipeRegistryAccessor().callMarkDummy(rl, id);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RecipeRemoval extends MixinAccessibleActionReloadCallback<MCRecipeManager.ActionBaseRemoveRecipes, ActionBaseRemoveRecipesAccessor> {
        private Int2ObjectArrayMap<IRecipe> snapshot = new Int2ObjectArrayMap<>();

        public RecipeRemoval(MCRecipeManager.ActionBaseRemoveRecipes action) {
            super(action);
        }

        @Override
        public void afterApply(boolean reload) {
            snapshot = getActionAccessor().getRemovedRecipes();
            for (Int2ObjectMap.Entry<IRecipe> entry : snapshot.int2ObjectEntrySet()) {
                getRecipeRegistryAccessor().callMarkDummy(entry.getValue().getRegistryName(), entry.getIntKey());
            }
        }

        @Override
        public void undo() {
            for (Int2ObjectMap.Entry<IRecipe> entry : snapshot.int2ObjectEntrySet()) {
                getRecipeRegistryAccessor().callAdd(entry.getIntKey(), entry.getValue(), Objects.requireNonNull(entry.getValue().getRegistryName()).getNamespace());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
