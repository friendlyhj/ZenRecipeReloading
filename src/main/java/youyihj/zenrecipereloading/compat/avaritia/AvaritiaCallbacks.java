package youyihj.zenrecipereloading.compat.avaritia;

import morph.avaritia.compat.crafttweaker.AddRecipeAction;
import morph.avaritia.compat.crafttweaker.RemoveRecipeAction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import youyihj.zenrecipereloading.mixins.avaritia.AddRecipeActionAccessor;
import youyihj.zenrecipereloading.mixins.avaritia.RemoveRecipeActionAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class AvaritiaCallbacks {
    public static class Add<R extends IForgeRegistryEntry<R>, A extends AddRecipeAction<R>> extends MixinAccessibleActionReloadCallback<A, AddRecipeActionAccessor<R>> {

        public Add(A action) {
            super(action);
        }

        @Override
        public void undo() {
            getActionAccessor().getRegistry().remove(getActionAccessor().getRecipe().getRegistryName());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove<R extends IForgeRegistryEntry<R>, A extends RemoveRecipeAction<R>> extends MixinAccessibleActionReloadCallback<A, RemoveRecipeActionAccessor<R>> {
        private List<R> snapshot;

        public Remove(A action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            Map<ResourceLocation, R> registry = getActionAccessor().getRegistry();
            snapshot = getActionAccessor().getFilter().apply(registry.values()).stream()
                    .map(registry::get)
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            Map<ResourceLocation, R> registry = getActionAccessor().getRegistry();
            snapshot.forEach(it -> registry.put(it.getRegistryName(), it));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
