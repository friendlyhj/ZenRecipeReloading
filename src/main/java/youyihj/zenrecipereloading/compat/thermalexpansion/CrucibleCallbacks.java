package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.CrucibleManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class CrucibleCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Crucible$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CrucibleManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private CrucibleManager.CrucibleRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = CrucibleManager.getRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                CrucibleManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
