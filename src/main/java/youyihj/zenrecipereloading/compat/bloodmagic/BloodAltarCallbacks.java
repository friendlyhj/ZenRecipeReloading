package youyihj.zenrecipereloading.compat.bloodmagic;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.api.impl.recipe.RecipeBloodAltar;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class BloodAltarCallbacks {
    public static class Remove extends PrivateActionReloadCallback {

        private RecipeBloodAltar recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            ItemStack input = getActionField("input");
            recipe = BloodMagicAPI.INSTANCE.getRecipeRegistrar().getBloodAltar(input);
        }

        @Override
        public void undo() {
            if (recipe != null) {
                BloodMagicAPI.INSTANCE.getRecipeRegistrar().addBloodAltar(recipe.getInput(), recipe.getOutput(), recipe.getMinimumTier().ordinal(), recipe.getSyphon(), recipe.getConsumeRate(), recipe.getDrainRate());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            Ingredient input = getActionField("input");
            BloodMagicAPI.INSTANCE.getRecipeRegistrar().removeBloodAltar(input.getMatchingStacks()[0]);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
