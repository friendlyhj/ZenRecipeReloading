package youyihj.zenrecipereloading.compat.embers;

import teamroots.embers.compat.crafttweaker.Mixer;
import teamroots.embers.recipe.FluidMixingRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.MixerAddAccessor;
import youyihj.zenrecipereloading.mixins.embers.MixerRemoveByOutputAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class MixerCallbacks {
    public static class RemoveAll extends ActionReloadCallback<Mixer.RemoveAll> {
        private List<FluidMixingRecipe> backup;

        public RemoveAll(Mixer.RemoveAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = new ArrayList<>(RecipeRegistry.mixingRecipes);
        }

        @Override
        public void undo() {
            RecipeRegistry.mixingRecipes.addAll(backup);
        }
    }

    public static class RemoveByOutput extends MixinAccessibleActionReloadCallback<Mixer.RemoveByOutput, MixerRemoveByOutputAccessor> {
        private List<FluidMixingRecipe> backup;

        public RemoveByOutput(Mixer.RemoveByOutput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.mixingRecipes.stream().filter(it -> it.output.isFluidStackIdentical(getActionAccessor().getOutput())).collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.mixingRecipes.addAll(backup);
        }
    }

    public static class Add extends MixinAccessibleActionReloadCallback<Mixer.Add, MixerAddAccessor> {

        public Add(Mixer.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.mixingRecipes.remove(getActionAccessor().getRecipe());
        }
    }
}
