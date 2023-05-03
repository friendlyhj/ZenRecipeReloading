package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.BrewerManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class ImbuerCallbacks {
    public static String getActonName(String path) {
        return "com.blamejared.compat.thermalexpansion.Imbuer$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            BrewerManager.removeRecipe(getActionField("input"), getActionField("inputFluid"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private BrewerManager.BrewerRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = BrewerManager.getRecipe(getActionField("input"), getActionField("fluid"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                BrewerManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getInputFluid(), recipe.getOutputFluid());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
