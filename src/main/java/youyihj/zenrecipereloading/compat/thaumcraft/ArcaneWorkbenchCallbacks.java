package youyihj.zenrecipereloading.compat.thaumcraft;

import com.blamejared.compat.thaumcraft.handlers.handlers.ArcaneWorkbench;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;
import thaumcraft.api.crafting.IArcaneRecipe;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenrecipereloading.util.ReflectionAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class ArcaneWorkbenchCallbacks {
    public static class AddShapeless extends ReflectionAccessibleActionReloadCallback<ArcaneWorkbench.AddShapeless> {

        public AddShapeless(ArcaneWorkbench.AddShapeless action) {
            super(action);
        }

        @Override
        public void undo() {
            RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(getActionField("location"));
        }
    }

    public static class AddShaped extends ReflectionAccessibleActionReloadCallback<ArcaneWorkbench.AddShaped> {
        public AddShaped(ArcaneWorkbench.AddShaped action) {
            super(action);
        }

        @Override
        public void undo() {
            RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(getActionField("location"));
        }
    }

    public static class Remove extends MixinAccessibleActionReloadCallback<ArcaneWorkbench.Remove, ArcaneWorkbenchRemoveAccessor> {
        public Remove(ArcaneWorkbench.Remove action) {
            super(action);
        }

        @Override
        public void undo() {
            ForgeRegistries.RECIPES.registerAll(getActionAccessor().getRemoved().toArray(new IArcaneRecipe[0]));
        }
    }
}
