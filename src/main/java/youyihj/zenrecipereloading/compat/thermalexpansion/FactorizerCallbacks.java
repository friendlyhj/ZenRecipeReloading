package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import crafttweaker.IAction;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class FactorizerCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.devicemanagers.Factorizer$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ItemStack input = CraftTweakerMC.getItemStack(getActionField("input"));
            String type = getActionField("type").toString();
            switch (type) {
                case "Spilt":
                    FactorizerManager.removeRecipe(input, true);
                    break;
                case "Combine":
                    FactorizerManager.removeRecipe(input, false);
                    break;
                case "Both":
                    FactorizerManager.removeRecipe(input, true);
                    FactorizerManager.removeRecipe(input, false);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private FactorizerManager.FactorizerRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = FactorizerManager.getRecipe(CraftTweakerMC.getItemStack(getActionField("input")), getActionField("isSplit"));
        }

        @Override
        public void undo() {
            FactorizerManager.addRecipe(recipe.getInput(), recipe.getOutput(), getActionField("isSplit"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
