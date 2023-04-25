package youyihj.zenrecipereloading.compat.bloodmagic;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.api.impl.recipe.RecipeTartaricForge;
import crafttweaker.IAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.Arrays;

/**
 * @author youyihj
 */
public class TartaricForgeCallbacks {
    public static class Add extends PrivateActionReloadCallback {

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
            BloodMagicAPI.INSTANCE.getRecipeRegistrar().removeTartaricForge(stacks);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {
        private RecipeTartaricForge recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            ItemStack[] inputs = getActionField("inputs");
            recipe = BloodMagicAPI.INSTANCE.getRecipeRegistrar().getTartaricForge(Arrays.asList(inputs));
        }

        @Override
        public void undo() {
            if (recipe != null) {
                BloodMagicAPI.INSTANCE.getRecipeRegistrar().addTartaricForge(recipe.getOutput(), recipe.getMinimumSouls(), recipe.getSoulDrain(), recipe.getInput().toArray(new Ingredient[0]));
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
