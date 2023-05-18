package youyihj.zenrecipereloading;

import crafttweaker.mc1120.commands.CTChatCommand;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import youyihj.zenrecipereloading.command.ReloadJEICommand;
import youyihj.zenrecipereloading.compat.avaritia.AvaritiaModule;
import youyihj.zenrecipereloading.compat.bloodmagic.BloodMagicModule;
import youyihj.zenrecipereloading.compat.botania.BotaniaModule;
import youyihj.zenrecipereloading.compat.forestry.ForestryModule;
import youyihj.zenrecipereloading.compat.immersiveengineering.ImmersiveEngineeringModule;
import youyihj.zenrecipereloading.compat.jei.JEIModule;
import youyihj.zenrecipereloading.compat.modtweaker.ModTweakerModule;
import youyihj.zenrecipereloading.compat.thermalexpansion.ThermalExpansionModule;
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
    public static final String DEPENDENCIES = "required-after:zenutils@[1.13.3,);required-after:jei;required-after:modtweaker";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ZenRecipeReloading INSTANCE;

    public static Logger logger;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        CraftingRecipeCallbacks.getRecipeRegistryAccessor().setDummyFactory((rl) -> new DummyRecipe().setRegistryName(rl));
        ModuleRegistry.register(new VanillaModule());
        ModuleRegistry.register(new JEIModule());
        ModuleRegistry.register(new ModTweakerModule());
        if (Loader.isModLoaded("bloodmagic")) {
            ModuleRegistry.register(new BloodMagicModule());
        }
        if (Loader.isModLoaded("botania")) {
            ModuleRegistry.register(new BotaniaModule());
        }
        if (Loader.isModLoaded("forestry")) {
            ModuleRegistry.register(new ForestryModule());
        }
        if (Loader.isModLoaded("thermalexpansion")) {
            ModuleRegistry.register(new ThermalExpansionModule());
        }
        if (Loader.isModLoaded("avaritia")) {
            ModuleRegistry.register(new AvaritiaModule());
        }
        if (Loader.isModLoaded("immersiveengieering")) {
            ModuleRegistry.register(new ImmersiveEngineeringModule());
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
