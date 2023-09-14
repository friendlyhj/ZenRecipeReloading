package youyihj.zenrecipereloading.compat.dropt;

import com.codetaylor.mc.dropt.modules.dropt.compat.crafttweaker.ZenDropt;
import net.minecraft.util.text.TextComponentString;
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
        event.getRequester().sendMessage(new TextComponentString("Run `/dropt reload` command to reload dropt rules"));
    }
}
