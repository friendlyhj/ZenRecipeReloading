package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.MetalPressRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class MetalPressCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.MetalPress$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            MetalPressRecipe recipe = getActionField("recipe");
            MetalPressRecipe.recipeList.remove(recipe.mold, recipe);
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
            List<MetalPressRecipe> removedRecipes = getActionField("removedRecipes");
            for (MetalPressRecipe removedRecipe : removedRecipes) {
                MetalPressRecipe.recipeList.put(removedRecipe.mold, removedRecipe);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}