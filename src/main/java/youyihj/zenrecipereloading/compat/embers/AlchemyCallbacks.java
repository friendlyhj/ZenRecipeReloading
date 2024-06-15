package youyihj.zenrecipereloading.compat.embers;

import net.minecraft.item.ItemStack;
import teamroots.embers.compat.crafttweaker.Alchemy;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.AlchemyAddActionAccessor;
import youyihj.zenrecipereloading.mixins.embers.AlchemyRemoveActionAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class AlchemyCallbacks {

    public static class Add extends MixinAccessibleActionReloadCallback<Alchemy.Add, AlchemyAddActionAccessor> {

        public Add(Alchemy.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.alchemyRecipes.remove(getActionAccessor().getRecipe());
        }
    }

    public static class Remove extends MixinAccessibleActionReloadCallback<Alchemy.RemoveByOutput, AlchemyRemoveActionAccessor> {
        private List<AlchemyRecipe> backup;

        public Remove(Alchemy.RemoveByOutput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.alchemyRecipes.stream()
                    .filter(it -> ItemStack.areItemStacksEqual(it.result, getActionAccessor().getOutput()))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.alchemyRecipes.addAll(backup);
        }
    }

    public static class RemoveAll extends ActionReloadCallback<Alchemy.RemoveAll> {
        private List<AlchemyRecipe> backup;

        public RemoveAll(Alchemy.RemoveAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = new ArrayList<>(RecipeRegistry.alchemyRecipes);
        }

        @Override
        public void undo() {
            RecipeRegistry.alchemyRecipes.addAll(backup);
        }
    }
}
