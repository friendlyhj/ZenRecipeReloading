package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.RefineryManager;
import crafttweaker.IAction;
import net.minecraftforge.fluids.FluidStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class RefineryCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Refinery$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        private final boolean potion;

        public Add(IAction action, boolean potion) {
            super(action);
            this.potion = potion;
        }

        @Override
        public void undo() {
            FluidStack input = getActionField("input");
            if (potion) {
                RefineryManager.removeRecipe(input);
            } else {
                RefineryManager.removeRecipePotion(input);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private RefineryManager.RefineryRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            FluidStack input = getActionField("input");
            if (getActionField("potion")) {
                recipe = RefineryManager.getRecipePotion(input);
            } else {
                recipe = RefineryManager.getRecipe(input);
            }
        }

        @Override
        public void undo() {
            if (recipe != null) {
                if (getActionField("potion")) {
                    RefineryManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutputFluid(), recipe.getOutputItem(), recipe.getChance());
                } else {
                    RefineryManager.addRecipePotion(recipe.getEnergy(), recipe.getInput(), recipe.getOutputFluid());
                }
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
