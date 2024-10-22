package youyihj.zenrecipereloading.compat.thaumcraft;

import crafttweaker.IAction;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.internal.CommonInternals;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class InfusionCallbacks {
    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CommonInternals.craftingRecipeCatalog.remove((ResourceLocation) getActionField("resourceLocation"));
        }
    }

    public static class Remove extends PrivateActionReloadCallback {
        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CommonInternals.craftingRecipeCatalog.putAll(getActionField("removedRecipes"));
        }
    }
}
