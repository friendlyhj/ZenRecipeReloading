package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.FurnaceManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class RedstoneFurnaceCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.RedstoneFurnace$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FurnaceManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddPyrolysis extends PrivateActionReloadCallback {
        public AddPyrolysis(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FurnaceManager.removeRecipePyrolysis(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private FurnaceManager.FurnaceRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = FurnaceManager.getRecipe(getActionField("input"), false);
        }

        @Override
        public void undo() {
            if (recipe != null) {
                FurnaceManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemovePyrolysis extends PrivateActionReloadCallback {

        private FurnaceManager.FurnaceRecipe recipe;

        public RemovePyrolysis(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = FurnaceManager.getRecipe(getActionField("input"), true);
        }

        @Override
        public void undo() {
            if (recipe != null) {
                FurnaceManager.addRecipePyrolysis(recipe.getEnergy(), recipe.getInput(), recipe.getOutput(), recipe.getCreosote());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
