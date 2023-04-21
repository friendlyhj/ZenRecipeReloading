package youyihj.zenrecipereloading.module;

import youyihj.zenutils.api.reload.ScriptReloadEvent;


/**
 * @author youyihj
 */
public interface IModule {

    void registerReloadCallbacks();

    void onReloadPre(ScriptReloadEvent.Pre event);

    void onReloadPost(ScriptReloadEvent.Post event);
}
