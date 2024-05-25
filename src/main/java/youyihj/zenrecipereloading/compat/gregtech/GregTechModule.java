package youyihj.zenrecipereloading.compat.gregtech;

import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.integration.crafttweaker.recipe.MetaItemBracketHandler;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

public class GregTechModule extends PlainModule {
    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        MetaItemBracketHandler.rebuildComponentRegistry();
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        MetaItemBracketHandler.clearComponentRegistry();
        //noinspection UnstableApiUsage
        GTRecipeOreInput.refreshStackCache();
    }
}
