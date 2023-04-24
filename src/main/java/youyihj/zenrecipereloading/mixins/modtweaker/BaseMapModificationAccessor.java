package youyihj.zenrecipereloading.mixins.modtweaker;

import com.blamejared.mtlib.utils.BaseMapModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youyihj
 */
@Mixin(value = BaseMapModification.class, remap = false)
public interface BaseMapModificationAccessor<K, V> {
    @Accessor
    Map<K, V> getMap();

    @Accessor
    HashMap<K, V> getSuccessful();
}
