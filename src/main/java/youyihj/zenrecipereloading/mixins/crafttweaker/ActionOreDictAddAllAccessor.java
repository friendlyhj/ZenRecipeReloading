package youyihj.zenrecipereloading.mixins.crafttweaker;

import crafttweaker.mc1120.actions.ActionOreDictAddAll;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author youyihj
 */
@Mixin(value = ActionOreDictAddAll.class, remap = false)
public interface ActionOreDictAddAllAccessor {
    @Accessor
    String getIdTarget();

    @Accessor
    String getIdSource();
}
