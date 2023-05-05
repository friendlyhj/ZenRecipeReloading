package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class NumisticDynamoCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.dynamos.NumisticDynamo$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            NumismaticManager.removeFuel(getActionField("stack"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddGem extends PrivateActionReloadCallback {

        public AddGem(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            NumismaticManager.removeGemFuel(getActionField("stack"));
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
            energy = NumismaticManager.getFuelEnergy(getActionField("stack"));
        }

        @Override
        public void undo() {
            if (energy != 0) {
                NumismaticManager.addFuel(getActionField("stack"), energy);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveGem extends PrivateActionReloadCallback {

        private int energy;

        public RemoveGem(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            energy = NumismaticManager.getGemFuelEnergy(getActionField("stack"));
        }

        @Override
        public void undo() {
            if (energy != 0) {
                NumismaticManager.addGemFuel(getActionField("stack"), energy);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
