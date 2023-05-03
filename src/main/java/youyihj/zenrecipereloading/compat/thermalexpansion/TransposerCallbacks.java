package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.TransposerManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class TransposerCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Transposer$" + path;
    }

    public static class AddExtract extends PrivateActionReloadCallback {

        public AddExtract(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            TransposerManager.removeExtractRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddFill extends PrivateActionReloadCallback {

        public AddFill(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            TransposerManager.removeFillRecipe(getActionField("input"), getActionField("fluid"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveExtract extends PrivateActionReloadCallback {

        private TransposerManager.TransposerRecipe recipe;

        public RemoveExtract(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = TransposerManager.getExtractRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                TransposerManager.addExtractRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput(), recipe.getFluid(), recipe.getChance(), false);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveFill extends PrivateActionReloadCallback {

        private TransposerManager.TransposerRecipe recipe;

        public RemoveFill(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = TransposerManager.getFillRecipe(getActionField("input"), getActionField("fluid"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                TransposerManager.addFillRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput(), recipe.getFluid(), false);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
