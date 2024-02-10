package youyihj.zenrecipereloading.compat.pneumaticcraft;

import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.util.ListRemoval;
import youyihj.zenrecipereloading.mixins.pneumaticcraft.ListModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

public class ListRemovalCallback<T extends ListRemoval<E>, E> extends MixinAccessibleActionReloadCallback<T, ListModificationAccessor<E>> {
    public ListRemovalCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getEntries().addAll(getActionAccessor().getRecipes());
    }
}
