package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.dynamo.MagmaticManager;
import crafttweaker.IAction;
import net.minecraftforge.fluids.FluidStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class MagmaticDynamoCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.dynamos.MagmaticDynamo$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FluidStack stack = getActionField("stack");
            MagmaticManager.removeFuel(stack.getFluid().getName());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private int energy;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            FluidStack stack = getActionField("stack");
            energy = MagmaticManager.getFuelEnergy(stack);
        }

        @Override
        public void undo() {
            if (energy != 0) {
                FluidStack stack = getActionField("stack");
                MagmaticManager.addFuel(stack.getFluid().getName(), energy);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
