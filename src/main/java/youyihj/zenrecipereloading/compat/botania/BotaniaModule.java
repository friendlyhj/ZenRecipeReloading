package youyihj.zenrecipereloading.compat.botania;

import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class BotaniaModule extends PlainModule {
    public BotaniaModule() {
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.botania.handlers.Orechid$Add", OrechidCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.botania.handlers.Orechid$Remove", OrechidCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.botania.handlers.OrechidIgnem$Add", OrechidNetherCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.botania.handlers.OrechidIgnem$Remove", OrechidNetherCallbacks.Remove::new);
    }
}
