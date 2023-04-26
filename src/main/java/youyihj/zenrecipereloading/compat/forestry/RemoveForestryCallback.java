package youyihj.zenrecipereloading.compat.forestry;

import com.blamejared.mtlib.utils.BaseRemoveForestry;
import forestry.api.recipes.IForestryRecipe;
import youyihj.zenrecipereloading.mixins.forestry.BaseRemoveForestryAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youyihj
 */
public class RemoveForestryCallback<T extends BaseRemoveForestry<R>, R extends IForestryRecipe> extends MixinAccessibleActionReloadCallback<T, BaseRemoveForestryAccessor<R>> {
    private final List<R> removed = new ArrayList<>();

    public RemoveForestryCallback(T action) {
        super(action);
    }

    @Override
    public void beforeApply(boolean reload) {
        getActionAccessor().getProvider().recipes().stream().filter(action::checkIsRecipe).forEach(removed::add);
    }

    @Override
    public void undo() {
        removed.forEach(getActionAccessor().getProvider()::addRecipe);
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
