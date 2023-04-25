package youyihj.zenrecipereloading.compat.bloodmagic;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.api.impl.recipe.RecipeAlchemyArray;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class AlchemyArrayCallbacks {
    public static class Removal extends PrivateActionReloadCallback {

        private RecipeAlchemyArray recipe;

        public Removal(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            ItemStack input = getActionField("input");
            ItemStack catalyst = getActionField("catalyst");
            recipe = BloodMagicAPI.INSTANCE.getRecipeRegistrar().getAlchemyArray(input, catalyst);
        }

        @Override
        public void undo() {
            if (recipe != null) {
                BloodMagicAPI.INSTANCE.getRecipeRegistrar().addAlchemyArray(recipe.getInput(), recipe.getCatalyst(), recipe.getOutput(), recipe.getCircleTexture());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Addition extends PrivateActionReloadCallback {

        public Addition(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ItemStack input = getActionField("input");
            ItemStack catalyst = getActionField("catalyst");
            BloodMagicAPI.INSTANCE.getRecipeRegistrar().removeAlchemyArray(input, catalyst);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
