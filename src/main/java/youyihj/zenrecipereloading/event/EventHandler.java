package youyihj.zenrecipereloading.event;

import crafttweaker.mc1120.commands.SpecialMessagesChat;
import crafttweaker.mc1120.events.ScriptRunEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import youyihj.zenrecipereloading.compat.vanilla.CraftingRecipeCallbacks;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.module.ModuleRegistry;
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
        CraftingRecipeCallbacks.getRecipeRegistry().unfreeze();
        ModuleRegistry.modules.forEach(it -> it.onReloadPre(event));
    }

    @SubscribeEvent
    public static void onReloadPost(ScriptReloadEvent.Post event) {
        ModuleRegistry.modules.forEach(it -> it.onReloadPost(event));
        CraftingRecipeCallbacks.getRecipeRegistry().freeze();
        event.getRequester().sendMessage(
                SpecialMessagesChat.getClickableCommandText(TextFormatting.DARK_GREEN + "Reload JEI to review recipe modifications", "/ct jeiReload", true)
        );
    }
}
