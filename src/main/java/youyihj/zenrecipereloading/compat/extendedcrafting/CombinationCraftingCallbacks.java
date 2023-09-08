package youyihj.zenrecipereloading.compat.extendedcrafting;

import com.blakebr0.extendedcrafting.crafting.CombinationRecipe;
import com.blakebr0.extendedcrafting.crafting.CombinationRecipeManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;
import java.util.stream.Collectors;

public class CombinationCraftingCallbacks {
    public static String getActionName(String path) {
        return "com.blakebr0.extendedcrafting.compat.crafttweaker.CombinationCrafting$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CombinationRecipe recipe = getActionField("recipe");
            CombinationRecipeManager.getInstance().getRecipes().remove(recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private List<CombinationRecipe> backup;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = CombinationRecipeManager.getInstance().getRecipes().stream()
                    .filter(it -> it.getOutput().isItemEqual(getActionField("remove")))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            if (backup != null) {
                CombinationRecipeManager.getInstance().getRecipes().addAll(backup);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
