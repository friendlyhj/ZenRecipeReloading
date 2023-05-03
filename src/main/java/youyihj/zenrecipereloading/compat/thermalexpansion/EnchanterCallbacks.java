package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class EnchanterCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Enchanter$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            EnchanterManager.removeRecipe(getActionField("input"), getActionField("inputSecondary"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private EnchanterManager.EnchanterRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = EnchanterManager.getRecipe(getActionField("input"), getActionField("inputSecondary"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                EnchanterManager.addRecipe(recipe.getEnergy(), recipe.getPrimaryInput(), recipe.getSecondaryInput(), recipe.getOutput(), recipe.getExperience(), recipe.getType());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
