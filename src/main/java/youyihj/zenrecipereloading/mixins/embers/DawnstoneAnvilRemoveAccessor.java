package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.DawnstoneAnvil;

/**
 * @author youyihj
 */
@Mixin(value = DawnstoneAnvil.RemoveByInput.class, remap = false)
public interface DawnstoneAnvilRemoveAccessor {
    @Accessor
    ItemStack getBottom();

    @Accessor
    ItemStack getTop();
}
