package youyihj.zenrecipereloading;

import crafttweaker.mc1120.commands.CTChatCommand;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import youyihj.zenrecipereloading.command.ReloadJEICommand;
import youyihj.zenrecipereloading.compat.jei.JEIModule;
import youyihj.zenrecipereloading.compat.modtweaker.ModTweakerModule;
import youyihj.zenrecipereloading.compat.vanilla.CraftingRecipeCallbacks;
import youyihj.zenrecipereloading.compat.vanilla.DummyRecipe;
import youyihj.zenrecipereloading.compat.vanilla.VanillaModule;
import youyihj.zenrecipereloading.module.ModuleRegistry;

@Mod(
        modid = ZenRecipeReloading.MOD_ID,
        name = ZenRecipeReloading.MOD_NAME,
        version = ZenRecipeReloading.VERSION,
        dependencies = ZenRecipeReloading.DEPENDENCIES
)
public class ZenRecipeReloading {

    public static final String MOD_ID = "zenrecipereloading";
    public static final String MOD_NAME = "ZenRecipeReloading";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:zenutils@[1.13.3,);required-after:jei";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ZenRecipeReloading INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        CraftingRecipeCallbacks.getRecipeRegistryAccessor().setDummyFactory((rl) -> new DummyRecipe().setRegistryName(rl));
        ModuleRegistry.modules.add(new VanillaModule());
        ModuleRegistry.modules.add(new JEIModule());
        if (Loader.isModLoaded("modtweaker")) {
            ModuleRegistry.modules.add(new ModTweakerModule());
        }
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        CTChatCommand.registerCommand(new ReloadJEICommand());
    }
}
