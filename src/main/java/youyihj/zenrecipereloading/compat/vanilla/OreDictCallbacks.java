package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.mc1120.actions.ActionOreDictAddAll;
import crafttweaker.mc1120.actions.ActionOreDictAddItem;
import crafttweaker.mc1120.actions.ActionOreDictRemoveItem;
import crafttweaker.mc1120.util.CraftTweakerHacks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictAddAllAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictAddItemAccessor;
import youyihj.zenrecipereloading.mixins.crafttweaker.ActionOreDictRemoveItemAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.List;
import java.util.Map;

/**
 * @author youyihj
 */
public class OreDictCallbacks {
    private static final Map<Integer, List<Integer>> stackToId;

    static {
        try {
            stackToId = CraftTweakerHacks.getPrivateStaticObject(OreDictionary.class, "stackToId");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int hashItemStack(ItemStack stack) {
        int hash;
        ResourceLocation registryName = stack.getItem().delegate.name();
        if (registryName == null) {
            return -1;
        }
        hash = Item.REGISTRY.getIDForObject(stack.getItem().delegate.get());
        if (stack.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
            hash |= ((stack.getItemDamage() + 1) << 16);
        }
        return hash;
    }

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
                NonNullList<ItemStack> list = OreDictionary.getOres(getActionAccessor().getIdTarget(), false);
                list.removeIf(it -> it.isItemEqual(item));
                stackToId.get(hashItemStack(item)).remove((Integer) OreDictionary.getOreID(getActionAccessor().getIdTarget()));
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
            NonNullList<ItemStack> list = OreDictionary.getOres(getActionAccessor().getId(), false);
            list.removeIf(it -> it.isItemEqual(getActionAccessor().getItem()));
            stackToId.get(hashItemStack(getActionAccessor().getItem())).remove((Integer) OreDictionary.getOreID(getActionAccessor().getId()));
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
        public void afterApply(boolean reload) {
            stackToId.get(hashItemStack(getActionAccessor().getItem())).remove((Integer) OreDictionary.getOreID(getActionAccessor().getId()));
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
