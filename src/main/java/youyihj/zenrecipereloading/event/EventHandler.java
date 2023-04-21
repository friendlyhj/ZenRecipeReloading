package youyihj.zenrecipereloading.event;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import crafttweaker.mc1120.commands.SpecialMessagesChat;
import crafttweaker.mc1120.events.ScriptRunEvent;
import crafttweaker.mc1120.furnace.MCFurnaceManager;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import mezz.jei.JustEnoughItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.module.ModuleRegistry;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void registerActionCallbacks(ScriptRunEvent.Pre event) {
        ModuleRegistry.modules.forEach(IModule::registerReloadCallbacks);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onReloadPre(ScriptReloadEvent.Pre event) {
        ModuleRegistry.modules.forEach(it -> it.onReloadPre(event));
    }

    @SubscribeEvent
    public static void onReloadPost(ScriptReloadEvent.Post event) {
        ModuleRegistry.modules.forEach(it -> it.onReloadPost(event));
        event.getRequester().sendMessage(
                SpecialMessagesChat.getClickableCommandText(TextFormatting.DARK_GREEN + "Reload JEI to review recipe modification", "/ct jeiReload", true)
        );
    }
}
