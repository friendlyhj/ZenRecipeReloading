package youyihj.zenrecipereloading.module;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author youyihj
 */
public class ModuleRegistry {
    public static final Collection<IModule> modules = new ArrayList<>();

    public static void register(IModule module) {
        modules.add(module);
    }
}
