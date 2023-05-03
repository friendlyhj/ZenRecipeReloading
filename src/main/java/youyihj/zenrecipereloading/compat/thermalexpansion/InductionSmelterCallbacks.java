package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class InductionSmelterCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.InductionSmelter$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {
        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            SmelterManager.removeRecipe(getActionField("primaryInput"), getActionField("secondaryInput"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private SmelterManager.SmelterRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = SmelterManager.getRecipe(getActionField("primaryInput"), getActionField("secondaryInput"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                SmelterManager.addRecipe(recipe.getEnergy(), recipe.getPrimaryInput(), recipe.getSecondaryInput(), recipe.getPrimaryOutput(), recipe.getSecondaryOutput(), recipe.getSecondaryOutputChance());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
