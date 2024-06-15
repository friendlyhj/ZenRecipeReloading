package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Stamper;

@Mixin(value = Stamper.RemoveByOutput.class, remap = false)
public interface StamperRemoveByOutputAccessor {
    @Accessor
    ItemStack getOutput();
}

