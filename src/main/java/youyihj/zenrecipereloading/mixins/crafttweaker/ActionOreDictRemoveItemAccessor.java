package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionOreDictRemoveItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = ActionOreDictRemoveItem.class, remap = false)
public interface ActionOreDictRemoveItemAccessor {
    @Accessor
    String getId();

    @Accessor
    ItemStack getItem();
}
