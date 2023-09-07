package youyihj.zenrecipereloading.mixins.gregtech;

import crafttweaker.CraftTweakerAPI;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.integration.crafttweaker.recipe.CTRecipeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import youyihj.zenrecipereloading.compat.gregtech.BuildRecipeAction;

/**
 * @author youyihj
 */
@Mixin(value = CTRecipeBuilder.class, remap = false)
public abstract class MixinCTRecipeBuilder {
    @Shadow
    @Final
    public RecipeBuilder<?> backingBuilder;

    @Inject(method = "buildAndRegister", at = @At("HEAD"), cancellable = true)
    public void migrateToAction(CallbackInfo ci) {
        CraftTweakerAPI.apply(new BuildRecipeAction<>(backingBuilder));
        ci.cancel();
    }
}
