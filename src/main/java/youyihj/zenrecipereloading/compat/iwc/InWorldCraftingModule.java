package youyihj.zenrecipereloading.compat.iwc;

import xt9.inworldcrafting.common.event.EntityMatcher;
import xt9.inworldcrafting.common.recipe.*;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
public class InWorldCraftingModule extends PlainModule {
    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        EntityMatcher.allValidInputs.clear();
        BurnItemRecipe.recipes.clear();
        ExplodeBlockRecipe.recipes.clear();
        ExplodeItemRecipe.recipes.clear();
        FluidToFluidRecipe.recipes.clear();
        FluidToItemRecipe.recipes.clear();
    }
}
