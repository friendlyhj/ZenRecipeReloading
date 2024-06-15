package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.HeatCoil;

/**
 * @author youyihj
 */
@Mixin(value = HeatCoil.RemoveByInput.class, remap = false)
public interface HeatCoilRemoveByInputAccessor {
    @Accessor
    ItemStack getInput();
}
