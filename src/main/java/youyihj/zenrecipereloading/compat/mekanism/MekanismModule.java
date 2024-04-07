package youyihj.zenrecipereloading.compat.mekanism;

import mekanism.common.integration.crafttweaker.CrafttweakerIntegration;
import mekanism.common.integration.crafttweaker.util.RecipeMapModification;
import youyihj.zenrecipereloading.module.PlainModule;

public class MekanismModule extends PlainModule {
    public MekanismModule() {
        trackActions(CrafttweakerIntegration.LATE_REMOVALS);
        trackActions(CrafttweakerIntegration.LATE_ADDITIONS);
        addCallbackFactory(RecipeMapModification.class, RecipeMapModificationCallback::new);
    }
}
