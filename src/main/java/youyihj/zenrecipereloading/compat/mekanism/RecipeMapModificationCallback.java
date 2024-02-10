package youyihj.zenrecipereloading.compat.mekanism;

import mekanism.common.integration.crafttweaker.util.RecipeMapModification;
import mekanism.common.recipe.inputs.MachineInput;
import mekanism.common.recipe.machines.MachineRecipe;
import youyihj.zenrecipereloading.mixins.mekanism.RecipeMapModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

public class RecipeMapModificationCallback<T extends RecipeMapModification<INPUT, RECIPE>, INPUT extends MachineInput<INPUT>, RECIPE extends MachineRecipe<INPUT, ?, RECIPE>>
        extends MixinAccessibleActionReloadCallback<T, RecipeMapModificationAccessor<INPUT, RECIPE>> {
    public RecipeMapModificationCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        RecipeMapModificationAccessor<INPUT, RECIPE> accessor = getActionAccessor();
        if (accessor.isAdd()) {
            accessor.getRecipes().forEach((input, recipe) -> accessor.getMap().remove(input, recipe));
        } else {
            accessor.getRecipes().forEach((input, recipe) -> accessor.getMap().put(input, recipe));
        }
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
