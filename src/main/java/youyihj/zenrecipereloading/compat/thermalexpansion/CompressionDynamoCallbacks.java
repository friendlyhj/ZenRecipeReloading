package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.dynamo.CompressionManager;
import crafttweaker.IAction;
import net.minecraftforge.fluids.FluidStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class CompressionDynamoCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.dynamos.CompressionDynamo$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FluidStack stack = getActionField("stack");
            CompressionManager.removeFuel(stack.getFluid().getName());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private int prevValue;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            FluidStack stack = getActionField("stack");
            prevValue = CompressionManager.getFuelEnergy(stack);
        }

        @Override
        public void undo() {
            if (prevValue != 0) {
                FluidStack stack = getActionField("stack");
                CompressionManager.addFuel(stack.getFluid().getName(), prevValue);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
