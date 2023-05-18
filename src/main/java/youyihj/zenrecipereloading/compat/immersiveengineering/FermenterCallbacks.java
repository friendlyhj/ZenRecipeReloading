package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.FermenterRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class FermenterCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Fermenter$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FermenterRecipe recipe = getActionField("recipe");
            FermenterRecipe.recipeList.remove(recipe);
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
            List<FermenterRecipe> removedRecipes = getActionField("removedRecipes");
            FermenterRecipe.recipeList.addAll(removedRecipes);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}