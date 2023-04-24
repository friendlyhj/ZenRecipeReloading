package youyihj.zenrecipereloading.compat.modtweaker;

import com.blamejared.mtlib.utils.BaseListAddition;
import youyihj.zenrecipereloading.mixins.modtweaker.BaseListModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class BaseListAdditionCallback<T extends BaseListAddition<R>, R> extends MixinAccessibleActionReloadCallback<T, BaseListModificationAccessor<R>> {
    public BaseListAdditionCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getList().removeAll(getActionAccessor().getSuccessful());
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
