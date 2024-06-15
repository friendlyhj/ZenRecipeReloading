package youyihj.zenrecipereloading.mixins.embers;

import crafttweaker.api.item.IIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.EmberGeneration;

@Mixin(value = EmberGeneration.AddCatalysisFuel.class, remap = false)
public interface AddCatalysisFuelAccessor {
    @Accessor
    IIngredient getItem();
}

