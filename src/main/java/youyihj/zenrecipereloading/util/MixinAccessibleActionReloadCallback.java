package youyihj.zenrecipereloading.util;

import crafttweaker.IAction;
import youyihj.zenutils.api.reload.ActionReloadCallback;

/**
 * @author youyihj
 */
public abstract class MixinAccessibleActionReloadCallback<T extends IAction, ACCESSOR> extends ActionReloadCallback<T> {
    public MixinAccessibleActionReloadCallback(T action) {
        super(action);
    }

    @SuppressWarnings("unchecked")
    protected ACCESSOR getActionAccessor() {
        return ((ACCESSOR) action);
    }
}
