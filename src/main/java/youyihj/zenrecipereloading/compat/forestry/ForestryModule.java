package youyihj.zenrecipereloading.compat.forestry;

import com.blamejared.mtlib.utils.BaseAddForestry;
import com.blamejared.mtlib.utils.BaseRemoveForestry;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class ForestryModule extends PlainModule {
    public ForestryModule() {
        addCallbackFactory(BaseRemoveForestry.class, RemoveForestryCallback::new);
        addCallbackFactory(BaseAddForestry.class, AddForestryCallback::new);
    }

    @Override
    public void registerReloadCallbacks() {
        super.registerReloadCallbacks();
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.forestry.CharcoalPile$Add", CharcoalPileCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.forestry.CharcoalPile$Remove", CharcoalPileCallbacks.Remove::new);
    }
}
