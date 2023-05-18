package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.BlueprintCraftingRecipe;
import com.google.common.collect.ArrayListMultimap;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class BlueprintCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Blueprint$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            BlueprintCraftingRecipe recipe = getActionField("recipe");
            BlueprintCraftingRecipe.recipeList.remove(recipe.blueprintCategory, recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            List<BlueprintCraftingRecipe> removedRecipes = getActionField("removedRecipes");
            for (BlueprintCraftingRecipe recipe : removedRecipes) {
                BlueprintCraftingRecipe.recipeList.remove(recipe.blueprintCategory, recipe);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveAll extends PrivateActionReloadCallback {

        public RemoveAll(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ArrayListMultimap<String, BlueprintCraftingRecipe> removedRecipes = getActionField("removedRecipes");
            BlueprintCraftingRecipe.recipeList.putAll(removedRecipes);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}