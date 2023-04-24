package youyihj.zenrecipereloading.core;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.Loader;
import youyihj.zenutils.api.util.ReflectionInvoked;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
@ReflectionInvoked
public class MixinLateInit implements ILateMixinLoader {
    private final List<String> mods = Lists.newArrayList(
            "crafttweaker",
            "jei",
            "modtweaker"
    );

    @Override
    public List<String> getMixinConfigs() {
        return mods.stream().map(it -> String.format("mixins.zenrecipereloading.%s.json", it)).collect(Collectors.toList());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        String[] parts = mixinConfig.split("\\.");
        return parts.length != 4 || Loader.isModLoaded(parts[2]);
    }
}
