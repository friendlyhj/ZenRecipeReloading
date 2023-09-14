package youyihj.zenrecipereloading.compat.patchouli;

import vazkii.patchouli.api.PatchouliAPI;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

public class PatchouliModule implements IModule {
    @Override
    public void registerReloadCallbacks() {

    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {

    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        PatchouliAPI.instance.reloadBookContents();
    }
}
