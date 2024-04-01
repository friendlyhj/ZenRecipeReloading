package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.AlloyRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class AlloySmelterCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.AlloySmelter$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            AlloyRecipe recipe = getActionField("recipe");
            AlloyRecipe.recipeList.remove(recipe);
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
            List<AlloyRecipe> removed = getActionField("removedRecipes");
            AlloyRecipe.recipeList.addAll(removed);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}