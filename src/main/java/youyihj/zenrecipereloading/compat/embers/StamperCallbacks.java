package youyihj.zenrecipereloading.compat.embers;

import net.minecraft.item.ItemStack;
import teamroots.embers.compat.crafttweaker.Stamper;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.StamperAddAccessor;
import youyihj.zenrecipereloading.mixins.embers.StamperRemoveByOutputAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class StamperCallbacks {
    public static class RemoveAll extends ActionReloadCallback<Stamper.RemoveAll> {
        private List<ItemStampingRecipe> backup;

        public RemoveAll(Stamper.RemoveAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = new ArrayList<>(RecipeRegistry.stampingRecipes);
        }

        @Override
        public void undo() {
            RecipeRegistry.stampingRecipes.addAll(backup);
        }
    }

    public static class RemoveByOutput extends MixinAccessibleActionReloadCallback<Stamper.RemoveByOutput, StamperRemoveByOutputAccessor> {
        private List<ItemStampingRecipe> backup;

        public RemoveByOutput(Stamper.RemoveByOutput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.stampingRecipes.stream().filter(it -> ItemStack.areItemsEqual(it.result, getActionAccessor().getOutput())).collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.stampingRecipes.addAll(backup);
        }
    }

    public static class Add extends MixinAccessibleActionReloadCallback<Stamper.Add, StamperAddAccessor> {

        public Add(Stamper.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.stampingRecipes.remove(getActionAccessor().getRecipe());
        }
    }
}
