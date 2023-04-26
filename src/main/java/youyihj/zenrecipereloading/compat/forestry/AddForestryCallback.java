package youyihj.zenrecipereloading.compat.forestry;

import com.blamejared.mtlib.utils.BaseAddForestry;
import forestry.api.recipes.IForestryRecipe;
import youyihj.zenrecipereloading.mixins.forestry.BaseAddForestryAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class AddForestryCallback<T extends BaseAddForestry<R>, R extends IForestryRecipe> extends MixinAccessibleActionReloadCallback<T, BaseAddForestryAccessor<R>> {
    public AddForestryCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getProvider().removeRecipe(getActionAccessor().getRecipe());
    }

    @Override
    public boolean hasUndoMethod() {
        return true;
    }
}
