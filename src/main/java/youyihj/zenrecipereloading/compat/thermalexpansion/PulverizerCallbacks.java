package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class PulverizerCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Pulverizer$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            PulverizerManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {
        private PulverizerManager.PulverizerRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = PulverizerManager.getRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                PulverizerManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getPrimaryOutput(), recipe.getSecondaryOutput(), recipe.getSecondaryOutputChance());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
