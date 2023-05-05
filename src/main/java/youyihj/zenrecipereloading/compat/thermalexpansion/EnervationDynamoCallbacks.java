package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.dynamo.EnervationManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class EnervationDynamoCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.dynamos.EnervationDynamo$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            EnervationManager.removeFuel(getActionField("stack"));
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
            energy = EnervationManager.getFuelEnergy(getActionField("stack"));
        }

        @Override
        public void undo() {
            if (energy != 0) {
                EnervationManager.addFuel(getActionField("stack"), energy);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
