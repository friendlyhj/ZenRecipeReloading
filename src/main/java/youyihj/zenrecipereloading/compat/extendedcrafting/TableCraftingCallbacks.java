package youyihj.zenrecipereloading.compat.extendedcrafting;

import com.blakebr0.extendedcrafting.crafting.table.ITieredRecipe;
import com.blakebr0.extendedcrafting.crafting.table.TableRecipeManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;
import java.util.stream.Collectors;

public class TableCraftingCallbacks {
    public static String getActionName(String path) {
        return "com.blakebr0.extendedcrafting.compat.crafttweaker.TableCrafting$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ITieredRecipe recipe = getActionField("recipe");
            TableRecipeManager.getInstance().getRecipes().remove(recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private List<ITieredRecipe> backup;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = TableRecipeManager.getInstance().getRecipes().stream()
                    .filter(it -> it.getRecipeOutput().isItemEqual(getActionField("remove")))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            if (backup != null) {
                TableRecipeManager.getInstance().getRecipes().addAll(backup);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
