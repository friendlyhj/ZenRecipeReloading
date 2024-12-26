package youyihj.zenrecipereloading.mixins.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author youyihj
 */
@Mixin(value = ArcFurnaceRecipe.class, remap = false)
public class MixinArcFurnaceRecipe {
    @WrapOperation(method = "removeRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/oredict/OreDictionary;itemMatches(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;Z)Z"))
    private static boolean fixRemoveNPE(ItemStack target, ItemStack input, boolean strict, Operation<Boolean> original) {
        return target != null && input != null && original.call(target, input, strict);
    }
}
