package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.item.ItemMorb;
import cofh.thermalexpansion.util.managers.machine.CentrifugeManager;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class CentrifugeCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.Centrifuge$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            CentrifugeManager.removeRecipe(getActionField("input"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddMob extends PrivateActionReloadCallback {

        public AddMob(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            String entityId = getActionField("entityId");
            ItemStack standard = ItemMorb.setTag(ItemHelper.cloneStack(ItemMorb.morbStandard), entityId, false);
            ItemStack reusable = ItemMorb.setTag(ItemHelper.cloneStack(ItemMorb.morbReusable), entityId, false);
            CentrifugeManager.removeRecipeMob(standard);
            CentrifugeManager.removeRecipeMob(reusable);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private CentrifugeManager.CentrifugeRecipe recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            recipe = CentrifugeManager.getRecipe(getActionField("input"));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                CentrifugeManager.addRecipe(recipe.getEnergy(), recipe.getInput(), recipe.getOutput(), recipe.getChance(), recipe.getFluid());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveMob extends PrivateActionReloadCallback {
        private CentrifugeManager.CentrifugeRecipe standardRecipe;
        private CentrifugeManager.CentrifugeRecipe reusableRecipe;


        public RemoveMob(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            String entityId = getActionField("entityId");
            ItemStack standard = ItemMorb.setTag(ItemHelper.cloneStack(ItemMorb.morbStandard), entityId, false);
            ItemStack reusable = ItemMorb.setTag(ItemHelper.cloneStack(ItemMorb.morbReusable), entityId, false);
            standardRecipe = CentrifugeManager.getRecipeMob(standard);
            reusableRecipe = CentrifugeManager.getRecipeMob(reusable);
        }

        @Override
        public void undo() {
            if (standardRecipe != null) {
                CentrifugeManager.addRecipeMob(standardRecipe.getEnergy(), standardRecipe.getInput(), standardRecipe.getOutput(), standardRecipe.getChance(), standardRecipe.getFluid());
            }
            if (reusableRecipe != null) {
                CentrifugeManager.addRecipeMob(reusableRecipe.getEnergy(), reusableRecipe.getInput(), reusableRecipe.getOutput(), reusableRecipe.getChance(), reusableRecipe.getFluid());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
