package youyihj.zenrecipereloading.util;

import crafttweaker.IAction;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;

/**
 * @author youyihj
 */
public abstract class PrivateActionReloadCallback extends ReflectionAccessibleActionReloadCallback<IAction> {

    public PrivateActionReloadCallback(IAction action) {
        super(action);
    }

    @SuppressWarnings("unchecked")
    public static void uncheckedRegister(String className, IActionReloadCallbackFactory<IAction> factory) {
        try {
            Class<IAction> clazz = (Class<IAction>) Class.forName(className);
            IActionReloadCallbackFactory.register(clazz, factory);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No such action class", e);
        }
    }
}
