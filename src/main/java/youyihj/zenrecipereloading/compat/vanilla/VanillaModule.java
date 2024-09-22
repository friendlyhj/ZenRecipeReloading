package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.mc1120.CraftTweaker;
import crafttweaker.mc1120.actions.*;
import crafttweaker.mc1120.furnace.MCFurnaceManager;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionRemoveRecipesNoIngredientsAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.MCRecipeManagerAccessor;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
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
        trackActions(CraftTweaker.LATE_ACTIONS);
        addCallbackFactory(MCRecipeManager.ActionBaseRemoveRecipes.class, CraftingRecipeCallbacks.RecipeRemoval::new);
        addCallbackFactory(MCRecipeManager.ActionBaseAddRecipe.class, CraftingRecipeCallbacks.RecipeAddition::new);
        addCallbackFactory(ActionAddFurnaceRecipe.class, FurnaceRecipeCallbacks.Addition::new);
        addCallbackFactory(ActionFurnaceRemoveRecipe.class, FurnaceRecipeCallbacks.Removal::new);
        addCallbackFactory(ActionSetFuel.class, FurnaceRecipeCallbacks.SetFuel::new);
        addCallbackFactory(ActionOreDictAddAll.class, OreDictCallbacks.AddAll::new);
        addCallbackFactory(ActionOreDictAddItem.class, OreDictCallbacks.AddItem::new);
        addCallbackFactory(ActionOreDictRemoveItem.class, OreDictCallbacks.RemoveItem::new);
    }

    @Override
    public void registerReloadCallbacks() {
        super.registerReloadCallbacks();

        PrivateActionReloadCallback.uncheckedRegister(TooltipCallbacks.getActionName("AddTooltipAction"), TooltipCallbacks.AddTooltipAction::new);
        PrivateActionReloadCallback.uncheckedRegister(TooltipCallbacks.getActionName("RemoveTooltipAction"), TooltipCallbacks.RemoveTooltipAction::new);
        PrivateActionReloadCallback.uncheckedRegister(TooltipCallbacks.getActionName("AddAdvancedTooltipAction"), TooltipCallbacks.AddAdvancedTooltipAction::new);
        PrivateActionReloadCallback.uncheckedRegister(TooltipCallbacks.getActionName("ClearTooltipAction"), TooltipCallbacks.ClearTooltipAction::new);
        PrivateActionReloadCallback.uncheckedRegister(TooltipCallbacks.getActionName("RemoveTooltipLineAction"), TooltipCallbacks.RemoveTooltipLineAction::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        ZenUtilsGlobal.addRegexLogFilter("Recipe name \\[.*\\] has duplicate uses, defaulting to calculated hash!");
        MCRecipeManager.refreshRecipes();
        ((ActionRemoveRecipesNoIngredientsAccessor) MCRecipeManager.actionRemoveRecipesNoIngredients).getOutputs().clear();
        MCRecipeManagerAccessor.getUsedHashes().clear();
        MCRecipeManagerAccessor.getUsedRecipeNames().clear();
        MCRecipeManager.transformerRecipes.clear();
        MCRecipeManager.actionRecipes.clear();
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
