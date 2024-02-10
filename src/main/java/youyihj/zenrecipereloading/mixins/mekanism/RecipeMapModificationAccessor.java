package youyihj.zenrecipereloading.mixins.mekanism;

import mekanism.common.integration.crafttweaker.util.RecipeMapModification;
import mekanism.common.recipe.inputs.MachineInput;
import mekanism.common.recipe.machines.MachineRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = RecipeMapModification.class, remap = false)
public interface RecipeMapModificationAccessor<INPUT extends MachineInput<INPUT>, RECIPE extends MachineRecipe<INPUT, ?, RECIPE>> {
    @Accessor
    Map<INPUT, RECIPE> getRecipes();

    @Accessor
    Map<INPUT, RECIPE> getMap();

    @Accessor
    boolean isAdd();
}
