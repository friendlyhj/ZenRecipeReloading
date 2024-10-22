package youyihj.zenrecipereloading.compat.thaumcraft;

import com.blamejared.compat.thaumcraft.handlers.handlers.Crucible;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.internal.CommonInternals;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenrecipereloading.util.ReflectionAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class CrucibleCallbacks {
    public static class Add extends ReflectionAccessibleActionReloadCallback<Crucible.Add> {

        public Add(Crucible.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            CommonInternals.craftingRecipeCatalog.remove((ResourceLocation) getActionField("name"));
        }
    }

    public static class Remove extends MixinAccessibleActionReloadCallback<Crucible.Remove, CrucibleRemoveAccessor> {

        public Remove(Crucible.Remove action) {
            super(action);
        }

        @Override
        public void undo() {
            CommonInternals.craftingRecipeCatalog.putAll(getActionAccessor().getRemoved());
        }
    }
}
