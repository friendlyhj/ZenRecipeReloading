package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.liquid.ILiquidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

import java.awt.*;

@Mixin(value = EmberGeneration.AddSteamEngineFuel.class, remap = false)
public interface AddSteamEngineFuelAccessor {
    @Accessor
    ILiquidStack getLiquid();
}
