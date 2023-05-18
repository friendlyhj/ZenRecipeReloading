package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.SqueezerRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class SqueezerCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Squeezer$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            SqueezerRecipe recipe = getActionField("recipe");
            SqueezerRecipe.recipeList.remove(recipe);
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
            List<SqueezerRecipe> removedRecipes = getActionField("removedRecipes");
            SqueezerRecipe.recipeList.addAll(removedRecipes);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}