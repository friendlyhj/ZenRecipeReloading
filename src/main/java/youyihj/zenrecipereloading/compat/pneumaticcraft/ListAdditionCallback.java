package youyihj.zenrecipereloading.compat.pneumaticcraft;

import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.util.ListAddition;
import youyihj.zenrecipereloading.mixins.pneumaticcraft.ListModificationAccessor;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

public class ListAdditionCallback<T extends ListAddition<E>, E> extends MixinAccessibleActionReloadCallback<T, ListModificationAccessor<E>> {
    public ListAdditionCallback(T action) {
        super(action);
    }

    @Override
    public void undo() {
        getActionAccessor().getEntries().removeAll(getActionAccessor().getRecipes());
    }
}
