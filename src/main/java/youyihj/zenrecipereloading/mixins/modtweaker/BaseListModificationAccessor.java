package youyihj.zenrecipereloading.mixins.modtweaker;

import com.blamejared.mtlib.utils.BaseListModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author youyihj
 */
@Mixin(value = BaseListModification.class, remap = false)
public interface BaseListModificationAccessor<T> {
    @Accessor
    LinkedList<T> getSuccessful();

    @Accessor
    List<T> getList();
}
