package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.IngredientStack;
import blusunrize.immersiveengineering.api.energy.ThermoelectricHandler;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

public class ThermoelectricCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Thermoelectric$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            IngredientStack ingredientStack = getActionField("ingredientStack");
            ThermoelectricHandler.temperatureMap.remove(ingredientStack);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private int temperature = -1;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            IngredientStack ingredientStack = getActionField("ingredientStack");
            if (ThermoelectricHandler.temperatureMap.containsKey(ingredientStack)) {
                temperature = ThermoelectricHandler.temperatureMap.get(ingredientStack);
            }
        }

        @Override
        public void undo() {
            if (temperature != -1) {
                IngredientStack ingredientStack = getActionField("ingredientStack");
                ThermoelectricHandler.registerSource(ingredientStack, temperature);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}