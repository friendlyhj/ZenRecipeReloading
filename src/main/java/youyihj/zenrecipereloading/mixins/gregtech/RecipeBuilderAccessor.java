package youyihj.zenrecipereloading.mixins.gregtech;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Consumer;

/**
 * @author youyihj
 */
@Mixin(value = RecipeBuilder.class, remap = false)
public interface RecipeBuilderAccessor<R extends RecipeBuilder<R>> {
    @Accessor
    Consumer<R> getOnBuildAction();

    @Accessor
    RecipeMap<R> getRecipeMap();
}
