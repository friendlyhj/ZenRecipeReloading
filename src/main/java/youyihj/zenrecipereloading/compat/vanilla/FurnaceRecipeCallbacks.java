package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.api.item.IItemStack;
import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import crafttweaker.mc1120.actions.ActionSetFuel;
import crafttweaker.mc1120.furnace.MCFurnaceManager;
import crafttweaker.mc1120.furnace.SetFuelPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionAddFurnaceRecipeAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionFurnaceRemoveRecipeAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionSetFuelAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.Map;

/**
 * @author youyihj
 */
public class FurnaceRecipeCallbacks {
    public static class Addition extends MixinAccessibleActionReloadCallback<ActionAddFurnaceRecipe, ActionAddFurnaceRecipeAccessor> {

        public Addition(ActionAddFurnaceRecipe action) {
            super(action);
        }

        @Override
        public void undo() {
            for (ItemStack itemStack : getActionAccessor().getInput()) {
                FurnaceRecipes.instance().getSmeltingList().remove(itemStack);
                FurnaceRecipes.instance().experienceList.remove(itemStack);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Removal extends MixinAccessibleActionReloadCallback<ActionFurnaceRemoveRecipe, ActionFurnaceRemoveRecipeAccessor> {

        public Removal(ActionFurnaceRemoveRecipe action) {
            super(action);
        }

        @Override
        public void undo() {
            for (Map.Entry<ItemStack, ItemStack> entry : getActionAccessor().getSmeltingMap().entrySet()) {
                // don't retain xp, I think it is ok?
                FurnaceRecipes.instance().addSmeltingRecipe(entry.getKey(), entry.getValue(), 0);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class SetFuel extends MixinAccessibleActionReloadCallback<ActionSetFuel, ActionSetFuelAccessor> {

        public SetFuel(ActionSetFuel action) {
            super(action);
        }

        @Override
        public void undo() {
            SetFuelPattern pattern = getActionAccessor().getPattern();
            for (IItemStack item : pattern.getPattern().getItems()) {
                MCFurnaceManager.fuelMap.put(item, -1);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
