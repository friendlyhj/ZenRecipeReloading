package youyihj.zenrecipereloading.util;

import crafttweaker.IAction;

public class RuntimePrivateActionReloadCallback extends PrivateActionReloadCallback {
    public RuntimePrivateActionReloadCallback(IAction action) {
        super(action);
    }

    @Override
    public boolean hasUndoMethod() {
        return false;
    }
}
