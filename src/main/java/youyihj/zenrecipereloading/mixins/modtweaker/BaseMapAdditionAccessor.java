package youyihj.zenrecipereloading.mixins.modtweaker;

import com.blamejared.mtlib.utils.BaseMapAddition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.HashMap;

/**
 * @author youyihj
 */
@Mixin(value = BaseMapAddition.class, remap = false)
public interface BaseMapAdditionAccessor<K, V> extends BaseMapModificationAccessor<K, V> {
    @Accessor
    HashMap<K, V> getOverwritten();
}
