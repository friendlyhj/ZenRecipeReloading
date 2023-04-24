package youyihj.zenrecipereloading.compat.modtweaker;

import com.blamejared.mtlib.utils.BaseMapAddition;
import youyihj.zenrecipereloading.mixins.modtweaker.BaseMapAdditionAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youyihj
 */
public class BaseMapAdditionCallback<T extends BaseMapAddition<K, V>, K, V> extends MixinAccessibleActionReloadCallback<T, BaseMapAdditionAccessor<K, V>> {
    public BaseMapAdditionCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        Map<K, V> map = getActionAccessor().getMap();
        HashMap<K, V> successful = getActionAccessor().getSuccessful();
        HashMap<K, V> overwritten = getActionAccessor().getOverwritten();
        for (K key : successful.keySet()) {
            if (overwritten.containsKey(key)) {
                map.put(key, overwritten.get(key));
            } else {
                map.remove(key);
            }
        }
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
