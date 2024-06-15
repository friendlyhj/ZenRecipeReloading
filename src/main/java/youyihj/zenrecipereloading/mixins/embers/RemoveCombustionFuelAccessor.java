package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.item.IItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

@Mixin(value = EmberGeneration.RemoveCombustionFuel.class, remap = false)
public interface RemoveCombustionFuelAccessor {
    @Accessor
    IItemStack getItem();
}

