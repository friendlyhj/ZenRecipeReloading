package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionFurnaceRemoveRecipe;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

/**
 * @author youyihj
 */
@Mixin(value = ActionFurnaceRemoveRecipe.class, remap = false)
public interface ActionFurnaceRemoveRecipeAccessor {
    @Accessor
    Map<ItemStack, ItemStack> getSmeltingMap();
}
