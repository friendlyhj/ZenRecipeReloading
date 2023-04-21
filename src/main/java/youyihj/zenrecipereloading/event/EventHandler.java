package youyihj.zenrecipereloading.event;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import crafttweaker.mc1120.events.ScriptRunEvent;
import crafttweaker.mc1120.furnace.MCFurnaceManager;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import mezz.jei.JustEnoughItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistry;
import youyihj.zenrecipereloading.compat.vanilla.CraftingRecipeCallbacks;
import youyihj.zenrecipereloading.compat.vanilla.FurnaceRecipeCallbacks;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionRemoveRecipesNoIngredientsAccessor;
import youyihj.zenrecipereloading.mixins.jei.JEIProxyClientAccessor;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void registerActionCallbacks(ScriptRunEvent.Pre event) {
        IActionReloadCallbackFactory.register(MCRecipeManager.ActionBaseAddRecipe.class, CraftingRecipeCallbacks.RecipeAddition::new);
        IActionReloadCallbackFactory.register(MCRecipeManager.ActionBaseRemoveRecipes.class, CraftingRecipeCallbacks.RecipeRemoval::new);
        IActionReloadCallbackFactory.register(ActionAddFurnaceRecipe.class, FurnaceRecipeCallbacks.Addition::new);
        IActionReloadCallbackFactory.register(ActionFurnaceRemoveRecipe.class, FurnaceRecipeCallbacks.Removal::new);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onReloadPre(ScriptReloadEvent.Pre event) {
        MCRecipeManager.refreshRecipes();
        MCRecipeManager.recipesToRemove.clear();
        MCRecipeManager.recipesToAdd.clear();
        MCFurnaceManager.recipesToAdd.clear();
        MCFurnaceManager.recipesToRemove.clear();
        ((ActionRemoveRecipesNoIngredientsAccessor) MCRecipeManager.actionRemoveRecipesNoIngredients).getOutputs().clear();
        CraftingRecipeCallbacks.getRecipeRegistry().unfreeze();
    }

    @SubscribeEvent
    public static void onReloadPost(ScriptReloadEvent.Post event) {
        CraftTweakerAPI.apply(MCRecipeManager.actionRemoveRecipesNoIngredients);
        MCRecipeManager.recipesToRemove.forEach(CraftTweakerAPI::apply);
        MCRecipeManager.recipesToAdd.forEach(CraftTweakerAPI::apply);
        MCFurnaceManager.recipesToAdd.forEach(CraftTweakerAPI::apply);
        MCFurnaceManager.recipesToRemove.forEach(CraftTweakerAPI::apply);
        CraftingRecipeCallbacks.getRecipeRegistry().freeze();
    }
}
