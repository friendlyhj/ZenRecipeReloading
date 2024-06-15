package youyihj.zenrecipereloading.compat.embers;

import teamroots.embers.compat.crafttweaker.DawnstoneAnvil;
import teamroots.embers.recipe.DawnstoneAnvilRecipe;
import teamroots.embers.recipe.RecipeRegistry;
import youyihj.zenrecipereloading.mixins.embers.*;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
public class DawnstoneAnvilCallbacks {
    public static class Add extends MixinAccessibleActionReloadCallback<DawnstoneAnvil.Add, DawnstoneAnvilAddAccessor> {
        public Add(DawnstoneAnvil.Add action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.dawnstoneAnvilRecipes.remove(getActionAccessor().getRecipe());
        }
    }

    public static class Remove extends MixinAccessibleActionReloadCallback<DawnstoneAnvil.RemoveByInput, DawnstoneAnvilRemoveAccessor> {
        private List<DawnstoneAnvilRecipe> backup;

        public Remove(DawnstoneAnvil.RemoveByInput action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = RecipeRegistry.dawnstoneAnvilRecipes.stream()
                    .filter(it -> it.top.apply(getActionAccessor().getTop()) && it.bottom.apply(getActionAccessor().getBottom()))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            RecipeRegistry.dawnstoneAnvilRecipes.addAll(backup);
        }
    }

    public static class BlacklistBreakdown extends MixinAccessibleActionReloadCallback<DawnstoneAnvil.BlacklistBreakdown, DawnstoneAnvilBlacklistBreakdownAccessor> {
        public BlacklistBreakdown(DawnstoneAnvil.BlacklistBreakdown action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.dawnstoneBreakdownBlacklist.remove(getActionAccessor().getMatch());
        }
    }

    public static class BlacklistMaterial extends MixinAccessibleActionReloadCallback<DawnstoneAnvil.BlacklistMateria, DawnstoneAnvilBlacklistMaterialAccessor> {
        public BlacklistMaterial(DawnstoneAnvil.BlacklistMateria action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.dawnstoneMateriaBlacklist.remove(getActionAccessor().getMatch());
        }
    }

    public static class BlacklistRepair extends MixinAccessibleActionReloadCallback<DawnstoneAnvil.BlacklistRepair, DawnstoneAnvilBlacklistRepairAccessor> {
        public BlacklistRepair(DawnstoneAnvil.BlacklistRepair action) {
            super(action);
        }

        @Override
        public void undo() {
            RecipeRegistry.dawnstoneRepairBlacklist.remove(getActionAccessor().getMatch());
        }
    }
}
