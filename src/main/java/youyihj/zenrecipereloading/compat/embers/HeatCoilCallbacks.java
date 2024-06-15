package youyihj.zenrecipereloading.compat.embers;

import teamroots.embers.compat.crafttweaker.HeatCoil;
import teamroots.embers.recipe.HeatCoilRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.HeatCoilAddAccessor;
import youyihj.zenrecipereloading.mixins.embers.HeatCoilRemoveByInputAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class HeatCoilCallbacks {
    public static class RemoveAll extends ActionReloadCallback<HeatCoil.RemoveAll> {
        private List<HeatCoilRecipe> backup;

        public RemoveAll(HeatCoil.RemoveAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = new ArrayList<>(RecipeRegistry.heatCoilRecipes);
        }

        @Override
        public void undo() {
            RecipeRegistry.heatCoilRecipes.addAll(backup);
        }
    }

    public static class RemoveByInput extends MixinAccessibleActionReloadCallback<HeatCoil.RemoveByInput, HeatCoilRemoveByInputAccessor> {
        private List<HeatCoilRecipe> backup;

        public RemoveByInput(HeatCoil.RemoveByInput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.heatCoilRecipes.stream().filter(it -> it.matches(getActionAccessor().getInput())).collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.heatCoilRecipes.addAll(backup);
        }
    }

    public static class Add extends MixinAccessibleActionReloadCallback<HeatCoil.Add, HeatCoilAddAccessor> {

        public Add(HeatCoil.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.heatCoilRecipes.remove(getActionAccessor().getRecipe());
        }
    }
}
