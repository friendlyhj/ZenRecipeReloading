package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import crafttweaker.mc1120.furnace.MCFurnaceManager;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionRemoveRecipesNoIngredientsAccessor;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenutils.api.reload.ScriptReloadEvent;
import youyihj.zenutils.api.util.ZenUtilsGlobal;

/**
 * @author youyihj
 */
public class VanillaModule extends PlainModule {
    public VanillaModule() {
        trackActions(MCRecipeManager.recipesToRemove);
        trackActions(MCRecipeManager.recipesToAdd);
        trackActions(MCFurnaceManager.recipesToRemove);
        trackActions(MCFurnaceManager.recipesToAdd);
        addCallbackFactory(MCRecipeManager.ActionBaseRemoveRecipes.class, CraftingRecipeCallbacks.RecipeRemoval::new);
        addCallbackFactory(MCRecipeManager.ActionBaseAddRecipe.class, CraftingRecipeCallbacks.RecipeAddition::new);
        addCallbackFactory(ActionAddFurnaceRecipe.class, FurnaceRecipeCallbacks.Addition::new);
        addCallbackFactory(ActionFurnaceRemoveRecipe.class, FurnaceRecipeCallbacks.Removal::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        ZenUtilsGlobal.addRegexLogFilter("Recipe name \\[.*\\] has duplicate uses, defaulting to calculated hash!");
        MCRecipeManager.refreshRecipes();
        ((ActionRemoveRecipesNoIngredientsAccessor) MCRecipeManager.actionRemoveRecipesNoIngredients).getOutputs().clear();
        super.onReloadPre(event);
        CraftingRecipeCallbacks.getRecipeRegistry().unfreeze();
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        CraftTweakerAPI.apply(MCRecipeManager.actionRemoveRecipesNoIngredients);
        super.onReloadPost(event);
        CraftingRecipeCallbacks.getRecipeRegistry().freeze();
    }
}
