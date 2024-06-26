package youyihj.zenrecipereloading.mixins.embers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import teamroots.embers.api.misc.IMetalCoefficient;
import teamroots.embers.compat.crafttweaker.EmberGeneration;
import youyihj.zenrecipereloading.compat.embers.AddMetalCoefficientAccessor;

/**
 * @author youyihj
 */
@Mixin(value = EmberGeneration.AddMetalCoefficient.class, remap = false)
public class MixinAddMetalCoefficient implements AddMetalCoefficientAccessor {
    @Unique
    private IMetalCoefficient metalCoefficient;

    @ModifyArg(method = "apply", at = @At(value = "INVOKE", target = "Lteamroots/embers/api/EmbersAPI;registerMetalCoefficient(Lteamroots/embers/api/misc/IMetalCoefficient;)V"))
    private IMetalCoefficient recordMetalCoefficient(IMetalCoefficient metalCoefficient) {
        this.metalCoefficient = metalCoefficient;
        return metalCoefficient;
    }

    @Override
    public IMetalCoefficient getMetalCoefficient() {
        return metalCoefficient;
    }
}
