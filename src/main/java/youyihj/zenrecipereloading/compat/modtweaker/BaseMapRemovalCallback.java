package youyihj.zenrecipereloading.compat.modtweaker;

import com.blamejared.mtlib.utils.BaseMapRemoval;
import youyihj.zenrecipereloading.mixins.modtweaker.BaseMapModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class BaseMapRemovalCallback<T extends BaseMapRemoval<K, V>, K, V> extends MixinAccessibleActionReloadCallback<T, BaseMapModificationAccessor<K, V>> {
    public BaseMapRemovalCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getMap().putAll(getActionAccessor().getSuccessful());
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
