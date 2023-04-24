package youyihj.zenrecipereloading.compat.modtweaker;

import com.blamejared.ModTweaker;
import com.blamejared.mtlib.utils.BaseListAddition;
import com.blamejared.mtlib.utils.BaseListRemoval;
import com.blamejared.mtlib.utils.BaseMapAddition;
import com.blamejared.mtlib.utils.BaseMapRemoval;
import youyihj.zenrecipereloading.module.PlainModule;

/**
 * @author youyihj
 */
public class ModTweakerModule extends PlainModule {
    public ModTweakerModule() {
        trackActions(ModTweaker.LATE_REMOVALS);
        trackActions(ModTweaker.LATE_ADDITIONS);
        addCallbackFactory(BaseListAddition.class, BaseListAdditionCallback::new);
        addCallbackFactory(BaseListRemoval.class, BaseListRemovalCallback::new);
        addCallbackFactory(BaseMapAddition.class, BaseMapAdditionCallback::new);
        addCallbackFactory(BaseMapRemoval.class, BaseMapRemovalCallback::new);
    }
}
