package youyihj.zenrecipereloading.mixins.gregtech;

import crafttweaker.CraftTweakerAPI;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import youyihj.zenrecipereloading.compat.gregtech.BackupRecipeAction;

/**
 * @author youyihj
 */
@Mixin(value = RecipeMap.class, remap = false)
public abstract class MixinRecipeMap {

    @Inject(method = "removeRecipe", at = @At("RETURN"))
    private void addBackup(Recipe recipe, CallbackInfoReturnable<Boolean> cir) {
        if (new Throwable().getStackTrace()[2].getClassName().endsWith("BuildRecipeAction")) {
            return;
        }
        if (cir.getReturnValue()) {
            CraftTweakerAPI.apply(new BackupRecipeAction(recipe, ((RecipeMap<?>) ((Object) this))));
        }
    }
}
