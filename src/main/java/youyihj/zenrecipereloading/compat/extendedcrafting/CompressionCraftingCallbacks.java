package youyihj.zenrecipereloading.compat.extendedcrafting;

import com.blakebr0.extendedcrafting.crafting.CompressorRecipe;
import com.blakebr0.extendedcrafting.crafting.CompressorRecipeManager;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;
import java.util.stream.Collectors;

public class CompressionCraftingCallbacks {
    public static String getActionName(String path) {
        return "com.blakebr0.extendedcrafting.compat.crafttweaker.CompressionCrafting$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CompressorRecipe recipe = getActionField("recipe");
            CompressorRecipeManager.getInstance().getRecipes().remove(recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private List<CompressorRecipe> backup;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = CompressorRecipeManager.getInstance().getRecipes().stream()
                    .filter(it -> it.getOutput().isItemEqual(getActionField("remove")))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            if (backup != null) {
                CompressorRecipeManager.getInstance().getRecipes().addAll(backup);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
