package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.RefineryRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class RefineryCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Refinery$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            RefineryRecipe recipe = getActionField("recipe");
            RefineryRecipe.recipeList.remove(recipe);
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
            List<RefineryRecipe> removedRecipes = getActionField("removedRecipes");
            RefineryRecipe.recipeList.addAll(removedRecipes);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}