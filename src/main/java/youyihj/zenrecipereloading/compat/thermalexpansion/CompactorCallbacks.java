package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.CompactorManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class CompactorCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Compactor$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CompactorManager.removeRecipe(getActionField("input"), getActionField("mode"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private CompactorManager.CompactorRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = CompactorManager.getRecipe(getActionField("input"), getActionField("mode"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                CompactorManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput(), getActionField("mode"));
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
