package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.ChargerManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class InfuserCallbacks {
    public static String getActonName(String path) {
        return "com.blamejared.compat.thermalexpansion.Infuser$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ChargerManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private ChargerManager.ChargerRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = ChargerManager.getRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                ChargerManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
