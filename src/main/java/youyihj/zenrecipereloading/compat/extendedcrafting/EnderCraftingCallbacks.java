package youyihj.zenrecipereloading.compat.extendedcrafting;

import com.blakebr0.extendedcrafting.crafting.endercrafter.EnderCrafterRecipeManager;
import crafttweaker.IAction;
import net.minecraft.item.crafting.IRecipe;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.List;
import java.util.stream.Collectors;

public class EnderCraftingCallbacks {
    public static String getActionName(String path) {
        return "com.blakebr0.extendedcrafting.compat.crafttweaker.EnderCrafting$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            IRecipe recipe = getActionField("recipe");
            EnderCrafterRecipeManager.getInstance().getRecipes().remove(recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private List<IRecipe> backup;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            backup = EnderCrafterRecipeManager.getInstance().getRecipes().stream()
                    .filter(it -> it.getRecipeOutput().isItemEqual(getActionField("remove")))
                    .collect(Collectors.toList());
        }

        @Override
        public void undo() {
            if (backup != null) {
                EnderCrafterRecipeManager.getInstance().getRecipes().addAll(backup);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
