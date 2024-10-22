package youyihj.zenrecipereloading.compat.thaumcraft;

import com.blamejared.compat.thaumcraft.handlers.handlers.ArcaneWorkbench;
import com.blamejared.compat.thaumcraft.handlers.handlers.Crucible;
import com.buuz135.thaumicjei.config.ThaumicConfig;
import net.minecraftforge.fml.common.Loader;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
public class ThaumcraftModule extends PlainModule {
    public ThaumcraftModule() {
        addCallbackFactory(ArcaneWorkbench.AddShaped.class, ArcaneWorkbenchCallbacks.AddShaped::new);
        addCallbackFactory(ArcaneWorkbench.AddShapeless.class, ArcaneWorkbenchCallbacks.AddShapeless::new);
        addCallbackFactory(ArcaneWorkbench.Remove.class, ArcaneWorkbenchCallbacks.Remove::new);
        addCallbackFactory(Crucible.Add.class, CrucibleCallbacks.Add::new);
        addCallbackFactory(Crucible.Remove.class, CrucibleCallbacks.Remove::new);
    }

    @Override
    public void registerReloadCallbacks() {
        super.registerReloadCallbacks();
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.thaumcraft.handlers.handlers.Infusion$Remove", InfusionCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.thaumcraft.handlers.handlers.Infusion$Add", InfusionCallbacks.Add::new);
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        if (Loader.isModLoaded("thaumicjei")) {
            ThaumicConfig.hideRecipesIfMissingResearch = false;
        }
    }
}
