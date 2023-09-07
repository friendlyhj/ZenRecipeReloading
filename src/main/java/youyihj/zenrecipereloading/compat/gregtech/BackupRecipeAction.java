package youyihj.zenrecipereloading.compat.gregtech;

import crafttweaker.IAction;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;

/**
 * @author youyihj
 */
@Reloadable
public class BackupRecipeAction implements IAction {
    private final Recipe backup;
    private final RecipeMap<?> recipeMap;

    public BackupRecipeAction(Recipe backup, RecipeMap<?> recipeMap) {
        this.backup = backup;
        this.recipeMap = recipeMap;
    }

    @Override
    public void apply() {

    }

    @Override
    public String describe() {
        return null;
    }

    @ReflectionInvoked
    public void undo() {
        recipeMap.addRecipe(ValidationResult.newResult(EnumValidationResult.VALID, backup));
    }
}
