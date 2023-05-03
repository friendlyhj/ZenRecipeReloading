package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class InsolatorCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Insolator$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            InsolatorManager.removeRecipe(getActionField("primaryInput"), getActionField("secondaryInput"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private InsolatorManager.InsolatorRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = InsolatorManager.getRecipe(getActionField("primaryInput"), getActionField("secondaryInput"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                InsolatorManager.addRecipe(recipe.getEnergy(), recipe.getWater(), recipe.getPrimaryInput(), recipe.getSecondaryInput(), recipe.getSecondaryInput(), recipe.getSecondaryOutput(), recipe.getSecondaryOutputChance(), recipe.getType());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
