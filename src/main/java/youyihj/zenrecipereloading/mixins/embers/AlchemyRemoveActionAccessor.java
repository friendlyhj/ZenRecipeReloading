package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Alchemy;

/**
 * @author youyihj
 */
@Mixin(value = Alchemy.RemoveByOutput.class, remap = false)
public interface AlchemyRemoveActionAccessor {
    @Accessor
    ItemStack getOutput();
}
