package youyihj.zenrecipereloading.compat.embers;

import teamroots.embers.compat.crafttweaker.Melter;
import teamroots.embers.recipe.ItemMeltingRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.MelterAddAccessor;
import youyihj.zenrecipereloading.mixins.embers.MelterRemoveByInputAccessor;
import youyihj.zenrecipereloading.mixins.embers.MelterRemoveByOutputAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class MelterCallbacks {
    public static class RemoveAll extends ActionReloadCallback<Melter.RemoveAll> {
        private List<ItemMeltingRecipe> backup;

        public RemoveAll(Melter.RemoveAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = new ArrayList<>(RecipeRegistry.meltingRecipes);
        }

        @Override
        public void undo() {
            RecipeRegistry.meltingRecipes.addAll(backup);
        }
    }

    public static class RemoveByInput extends MixinAccessibleActionReloadCallback<Melter.RemoveByInput, MelterRemoveByInputAccessor> {
        private List<ItemMeltingRecipe> backup;

        public RemoveByInput(Melter.RemoveByInput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.meltingRecipes.stream().filter(it -> it.input.apply(getActionAccessor().getInput())).collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.meltingRecipes.addAll(backup);
        }
    }

    public static class RemoveByOutput extends MixinAccessibleActionReloadCallback<Melter.RemoveByOutput, MelterRemoveByOutputAccessor> {
        private List<ItemMeltingRecipe> backup;

        public RemoveByOutput(Melter.RemoveByOutput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.meltingRecipes.stream().filter(it -> getActionAccessor().getOutput().isFluidStackIdentical(it.getFluid())).collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.meltingRecipes.addAll(backup);
        }
    }

    public static class Add extends MixinAccessibleActionReloadCallback<Melter.Add, MelterAddAccessor> {

        public Add(Melter.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.meltingRecipes.remove(getActionAccessor().getRecipe());
        }
    }
}
