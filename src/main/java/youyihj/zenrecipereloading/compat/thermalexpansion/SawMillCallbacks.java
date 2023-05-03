package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class SawMillCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.SawMill$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            SawmillManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private SawmillManager.SawmillRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = SawmillManager.getRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                SawmillManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getPrimaryOutput(), recipe.getSecondaryOutput(), recipe.getSecondaryOutputChance());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return false;
        }
    }
}
