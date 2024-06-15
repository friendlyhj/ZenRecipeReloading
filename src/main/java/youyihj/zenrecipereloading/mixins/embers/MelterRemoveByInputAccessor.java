package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Melter;

@Mixin(value = Melter.RemoveByInput.class, remap = false)
public interface MelterRemoveByInputAccessor {
    @Accessor
    ItemStack getInput();
}

