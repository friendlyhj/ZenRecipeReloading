package youyihj.zenrecipereloading.compat.jei;

import crafttweaker.mods.jei.JEI;
import crafttweaker.mods.jei.actions.*;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.RuntimeReloadCallback;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
public class JEIModule extends PlainModule {
    public JEIModule() {
        trackActions(JEI.LATE_ACTIONS_PRE);
        trackActions(JEI.LATE_ACTIONS_POST);
        addCallbackFactory(HideAction.class, RuntimeReloadCallback::new);
        addCallbackFactory(HideFluidAction.class, RuntimeReloadCallback::new);
        addCallbackFactory(HideCategoryAction.class, RuntimeReloadCallback::new);
        addCallbackFactory(DescribeAction.class, RuntimeReloadCallback::new);
        addCallbackFactory(AddItemAction.class, RuntimeReloadCallback::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        super.onReloadPre(event);
        JEI.DESCRIPTIONS.clear();
        JEI.HIDDEN_ITEMS.clear();
        JEI.HIDDEN_LIQUIDS.clear();
        JEI.HIDDEN_CATEGORIES.clear();
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        // shouldn't apply tracked actions, they are applied while jei reloading
    }
}
