package youyihj.zenrecipereloading.util;

import crafttweaker.IAction;
import youyihj.zenutils.api.reload.ActionReloadCallback;

/**
 * @author youyihj
 */
public class RuntimeReloadCallback<T extends IAction> extends ActionReloadCallback<T> {
    public RuntimeReloadCallback(T action) {
        super(action);
    }

    @Override
    public boolean hasUndoMethod() {
        return false;
    }
}
