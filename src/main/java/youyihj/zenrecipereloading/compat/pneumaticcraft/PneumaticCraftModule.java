package youyihj.zenrecipereloading.compat.pneumaticcraft;

import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.CraftTweaker;
import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.util.ListAddition;
import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.util.ListRemoval;
import youyihj.zenrecipereloading.module.PlainModule;

public class PneumaticCraftModule extends PlainModule {
    public PneumaticCraftModule() {
        trackActions(CraftTweaker.ADDITIONS);
        trackActions(CraftTweaker.REMOVALS);
        addCallbackFactory(ListAddition.class, ListAdditionCallback::new);
        addCallbackFactory(ListRemoval.class, ListRemovalCallback::new);
    }
}
