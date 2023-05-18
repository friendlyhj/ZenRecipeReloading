package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.CokeOvenRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class CokeOvenCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.CokeOven$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CokeOvenRecipe recipe = getActionField("recipe");
            CokeOvenRecipe.recipeList.remove(recipe);
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
            List<CokeOvenRecipe> removedRecipes = getActionField("removedRecipes");
            CokeOvenRecipe.recipeList.addAll(removedRecipes);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}