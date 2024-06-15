package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.liquid.ILiquidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

@Mixin(value = EmberGeneration.RemoveBoilerFluid.class, remap = false)
public interface RemoveBoilerFluidAccessor {
    @Accessor
    ILiquidStack getLiquid();
}