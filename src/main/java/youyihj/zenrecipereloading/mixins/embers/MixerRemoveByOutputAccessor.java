package youyihj.zenrecipereloading.mixins.embers;

import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.Mixer;

@Mixin(value = Mixer.RemoveByOutput.class, remap = false)
public interface MixerRemoveByOutputAccessor {
    @Accessor
    FluidStack getOutput();
}
