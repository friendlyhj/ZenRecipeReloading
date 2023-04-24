package youyihj.zenrecipereloading.compat.modtweaker;

import com.blamejared.mtlib.utils.BaseListRemoval;
import youyihj.zenrecipereloading.mixins.modtweaker.BaseListModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class BaseListRemovalCallback<T extends BaseListRemoval<R>, R> extends MixinAccessibleActionReloadCallback<T, BaseListModificationAccessor<R>> {
    public BaseListRemovalCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getList().addAll(getActionAccessor().getSuccessful());
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
