package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.mc1120.actions.ActionOreDictAddAll;
import crafttweaker.mc1120.actions.ActionOreDictAddItem;
import crafttweaker.mc1120.actions.ActionOreDictRemoveItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictAddAllAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictAddItemAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictRemoveItemAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class OreDictCallbacks {
    public static class AddAll extends MixinAccessibleActionReloadCallback<ActionOreDictAddAll, ActionOreDictAddAllAccessor> {

        private NonNullList<ItemStack> toAdd;

        public AddAll(ActionOreDictAddAll action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            toAdd = OreDictionary.getOres(getActionAccessor().getIdSource());
        }

        @Override
        public void undo() {
            for (ItemStack item : toAdd) {
                OreDictionary.getOres(getActionAccessor().getIdTarget(), false).remove(item);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddItem extends MixinAccessibleActionReloadCallback<ActionOreDictAddItem, ActionOreDictAddItemAccessor> {

        public AddItem(ActionOreDictAddItem action) {
            super(action);
        }

        @Override
        public void undo() {
            OreDictionary.getOres(getActionAccessor().getId(), false).remove(getActionAccessor().getItem());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveItem extends MixinAccessibleActionReloadCallback<ActionOreDictRemoveItem, ActionOreDictRemoveItemAccessor> {

        public RemoveItem(ActionOreDictRemoveItem action) {
            super(action);
        }

        @Override
        public void undo() {
            OreDictionary.registerOre(getActionAccessor().getId(), getActionAccessor().getItem());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
