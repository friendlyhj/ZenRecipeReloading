package youyihj.zenrecipereloading.compat.bloodmagic;

import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
public class BloodMagicModule implements IModule {
    @Override
    public void registerReloadCallbacks() {
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.AlchemyArray$Remove", AlchemyArrayCallbacks.Removal::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.AlchemyArray$Add", AlchemyArrayCallbacks.Addition::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.AlchemyTable$Add", AlchemyTableCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.AlchemyTable$AddPotion", AlchemyTableCallbacks.AddPotion::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.AlchemyTable$Remove", AlchemyTableCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.BloodAltar$Remove", BloodAltarCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.BloodAltar$Add", BloodAltarCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.TartaricForge$Remove", TartaricForgeCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.bloodmagic.TartaricForge$Add", TartaricForgeCallbacks.Add::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {

    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {

    }
}
