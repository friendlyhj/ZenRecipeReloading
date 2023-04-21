package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionAddFurnaceRecipeAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionFurnaceRemoveRecipeAccessor;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.util.Map;

/**
 * @author youyihj
 */
public class FurnaceRecipeCallbacks {
    public static class Addition extends ActionReloadCallback<ActionAddFurnaceRecipe> {

        public Addition(ActionAddFurnaceRecipe action) {
            super(action);
        }

        @Override
        public void undo() {
            ActionAddFurnaceRecipeAccessor actionAccessor = (ActionAddFurnaceRecipeAccessor) action;
            for (ItemStack itemStack : actionAccessor.getInput()) {
                FurnaceRecipes.instance().getSmeltingList().remove(itemStack);
                FurnaceRecipes.instance().experienceList.remove(itemStack);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Removal extends ActionReloadCallback<ActionFurnaceRemoveRecipe> {

        public Removal(ActionFurnaceRemoveRecipe action) {
            super(action);
        }

        @Override
        public void undo() {
            ActionFurnaceRemoveRecipeAccessor actionAccessor = (ActionFurnaceRemoveRecipeAccessor) action;
            for (Map.Entry<ItemStack, ItemStack> entry : actionAccessor.getSmeltingMap().entrySet()) {
                // don't retain xp, I think it is ok?
                FurnaceRecipes.instance().addSmeltingRecipe(entry.getKey(), entry.getValue(), 0);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}