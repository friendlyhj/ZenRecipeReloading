package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.BlastFurnaceRecipe;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;

public class BlastFurnaceCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.BlastFurnace$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            BlastFurnaceRecipe recipe = getActionField("recipe");
            BlastFurnaceRecipe.recipeList.remove(recipe);
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
            List<BlastFurnaceRecipe> removed = getActionField("removeRecipes");
            BlastFurnaceRecipe.recipeList.addAll(removed);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddFuel extends PrivateActionReloadCallback {
        public AddFuel(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            BlastFurnaceRecipe.BlastFurnaceFuel fuel = getActionField("fuelRecipeKey");
            BlastFurnaceRecipe.blastFuels.remove(fuel);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveFuel extends PrivateActionReloadCallback {
        public RemoveFuel(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            BlastFurnaceRecipe.BlastFurnaceFuel removed = getActionField("removed");
            if (removed != null) {
                BlastFurnaceRecipe.blastFuels.add(removed);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}