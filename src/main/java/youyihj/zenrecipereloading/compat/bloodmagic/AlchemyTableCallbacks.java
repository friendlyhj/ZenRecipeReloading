package youyihj.zenrecipereloading.compat.bloodmagic;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.api.impl.recipe.RecipeAlchemyTable;
import WayofTime.bloodmagic.core.registry.AlchemyTableRecipeRegistry;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.Arrays;
import java.util.List;

/**
 * @author youyihj
 */
public class AlchemyTableCallbacks {
    public static class Add extends PrivateActionReloadCallback {

//        private static Constructor<IAction> removeActionConstructor;
//
//        static {
//            try {
//                //noinspection unchecked
//                removeActionConstructor = (Constructor<IAction>) Class.forName("com.blamejared.compat.bloodmagic.AlchemyTable$Remove").getConstructor(ItemStack[].class);
//            } catch (ReflectiveOperationException e) {
//                throw new RuntimeException(e);
//            }
//        }

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            Object[] inputs = getActionField("inputs");
            ItemStack[] stacks = new ItemStack[inputs.length];
            Arrays.fill(stacks, ItemStack.EMPTY);
            for (int i = 0; i < inputs.length; i++) {
                Object input = inputs[i];
                if (input instanceof ItemStack) {
                    stacks[i] = ((ItemStack) input);
                } else if (input instanceof String) {
                    stacks[i] = OreDictionary.getOres(((String) input)).get(0);
                }
            }
            BloodMagicAPI.INSTANCE.getRecipeRegistrar().removeAlchemyTable(stacks);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddPotion extends PrivateActionReloadCallback {
        public AddPotion(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            List<ItemStack> inputs = getActionField("inputs");
            AlchemyTableRecipeRegistry.removeRecipe(AlchemyTableRecipeRegistry.getMatchingRecipe(inputs, null, null));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private RecipeAlchemyTable recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            ItemStack[] inputs = getActionField("inputs");
            recipe = BloodMagicAPI.INSTANCE.getRecipeRegistrar().getAlchemyTable(Arrays.asList(inputs));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                BloodMagicAPI.INSTANCE.getRecipeRegistrar().addAlchemyTable(recipe);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
