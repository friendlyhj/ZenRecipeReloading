package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionOreDictAddItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = ActionOreDictAddItem.class, remap = false)
public interface ActionOreDictAddItemAccessor {
    @Accessor
    String getId();

    @Accessor
    ItemStack getItem();
}
