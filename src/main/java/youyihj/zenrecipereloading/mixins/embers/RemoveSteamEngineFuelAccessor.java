package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.liquid.ILiquidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

@Mixin(value = EmberGeneration.RemoveSteamEngineFuel.class, remap = false)
public interface RemoveSteamEngineFuelAccessor {
    @Accessor
    ILiquidStack getLiquid();
}
