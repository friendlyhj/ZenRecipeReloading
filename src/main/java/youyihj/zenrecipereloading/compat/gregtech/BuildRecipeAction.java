package youyihj.zenrecipereloading.compat.gregtech;

import crafttweaker.IAction;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import youyihj.zenrecipereloading.mixins.gregtech.RecipeBuilderAccessor;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;

import java.util.function.Consumer;

/**
 * @author youyihj
 */
@Reloadable
public class BuildRecipeAction<R extends RecipeBuilder<R>> implements IAction {
    private final R recipeBuilder;
    private ValidationResult<Recipe> recipe;

    @SuppressWarnings("unchecked")
    public BuildRecipeAction(RecipeBuilder<?> recipeBuilder) {
        this.recipeBuilder = (R) recipeBuilder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void apply() {
        recipe = recipeBuilder.isCTRecipe().build();
        Consumer<R> onBuildAction = ((RecipeBuilderAccessor<R>) recipeBuilder).getOnBuildAction();
        if (onBuildAction != null) {
            onBuildAction.accept(recipeBuilder);
        }
        ((RecipeBuilderAccessor<?>) recipeBuilder).getRecipeMap().addRecipe(recipe);
    }

    @Override
    public String describe() {
        return "Adding a GregTech recipe: " + recipeBuilder;
    }

    @ReflectionInvoked
    public void undo() {
        if (recipe.getType() == EnumValidationResult.VALID) {
            ((RecipeBuilderAccessor<?>) recipeBuilder).getRecipeMap().removeRecipe(recipe.getResult());
        }
    }
}
