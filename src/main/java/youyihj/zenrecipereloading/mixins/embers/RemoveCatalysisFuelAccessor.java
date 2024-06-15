package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.item.IItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

@Mixin(value = EmberGeneration.RemoveCatalysisFuel.class, remap = false)
public interface RemoveCatalysisFuelAccessor {
    @Accessor
    IItemStack getItem();
}
