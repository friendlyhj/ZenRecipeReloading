package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionSetFuel;
import crafttweaker.mc1120.furnace.SetFuelPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = ActionSetFuel.class, remap = false)
public interface ActionSetFuelAccessor {
    @Accessor
    SetFuelPattern getPattern();
}
