package youyihj.zenrecipereloading.mixins.embers;

import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Melter;

@Mixin(value = Melter.RemoveByOutput.class, remap = false)
public interface MelterRemoveByOutputAccessor {
    @Accessor
    FluidStack getOutput();
}
