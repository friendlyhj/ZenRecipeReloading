package youyihj.zenrecipereloading.compat.artisanworktables;

import com.codetaylor.mc.artisanworktables.api.ArtisanRegistries;
import com.codetaylor.mc.artisanworktables.modules.worktables.ModuleWorktables;
import com.codetaylor.mc.artisanworktables.modules.worktables.integration.crafttweaker.CTActionAdd;
import com.codetaylor.mc.artisanworktables.modules.worktables.integration.crafttweaker.CTArtisanRecipe;
import com.codetaylor.mc.artisanworktables.modules.worktables.integration.crafttweaker.CTRecipeAdditionQueue;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.mc1120.CraftTweaker;
import crafttweaker.mc1120.events.ActionApplyEvent;
import youyihj.zenrecipereloading.mixins.artisanworktables.RecipeRegistryAccessor;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.util.RuntimeReloadCallback;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

public class ArtisanWorktablesModule implements IModule {
    @Override
    public void registerReloadCallbacks() {
        IActionReloadCallbackFactory.register(CTActionAdd.class, RuntimeReloadCallback::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        ArtisanRegistries.RECIPE_REGISTRY.forEach(it -> {
            RecipeRegistryAccessor registry = (RecipeRegistryAccessor) it;
            registry.getRecipeList().removeIf(CTArtisanRecipe.class::isInstance);
            registry.getRecipeMap().values().removeIf(CTArtisanRecipe.class::isInstance);
        });
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        CTRecipeAdditionQueue recipeAdditionQueue = (CTRecipeAdditionQueue) ModuleWorktables.RECIPE_ADDITION_QUEUE;
        // collect recipes to add actions
        recipeAdditionQueue.on(new ActionApplyEvent.Pre());
        CraftTweaker.LATE_ACTIONS.forEach(it -> {
            if (it instanceof CTActionAdd) {
                CraftTweakerAPI.apply(it);
            }
        });
        // add copy recipe
        recipeAdditionQueue.on(new ActionApplyEvent.Post());
    }
}
