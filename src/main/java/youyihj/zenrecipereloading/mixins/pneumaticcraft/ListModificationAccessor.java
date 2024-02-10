package youyihj.zenrecipereloading.mixins.pneumaticcraft;

import me.desht.pneumaticcraft.common.thirdparty.crafttweaker.util.ListModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = ListModification.class, remap = false)
public interface ListModificationAccessor<T> {
    @Accessor
    List<T> getRecipes();

    @Accessor
    List<T> getEntries();
}
