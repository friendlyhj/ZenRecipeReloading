package youyihj.zenrecipereloading.core;

import youyihj.zenutils.api.util.ReflectionInvoked;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

/**
 * @author youyihj
 */
@ReflectionInvoked
public class MixinLateInit implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.zenrecipereloading.mods.json");
    }
}
