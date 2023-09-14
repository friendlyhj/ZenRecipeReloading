package youyihj.zenrecipereloading.compat.dropt;

import com.codetaylor.mc.dropt.modules.dropt.compat.crafttweaker.ZenDropt;
import crafttweaker.mc1120.CraftTweaker;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

public class DroptModule implements IModule {
    @Override
    public void registerReloadCallbacks() {

    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        ZenDropt.LISTS.clear();
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        CraftTweaker.server.getCommandManager().executeCommand(event.getRequester(), "dropt reload");
    }
}
