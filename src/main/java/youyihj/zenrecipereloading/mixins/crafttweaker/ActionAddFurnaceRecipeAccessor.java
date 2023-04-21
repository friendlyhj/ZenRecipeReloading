package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionAddFurnaceRecipe;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = ActionAddFurnaceRecipe.class, remap = false)
public interface ActionAddFurnaceRecipeAccessor {
    @Accessor
    ItemStack[] getInput();
}
