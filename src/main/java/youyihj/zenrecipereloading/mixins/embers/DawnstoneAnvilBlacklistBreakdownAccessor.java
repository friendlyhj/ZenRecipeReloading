package youyihj.zenrecipereloading.mixins.embers;

import net.minecraft.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import teamroots.embers.compat.crafttweaker.DawnstoneAnvil;

/**
 * @author youyihj
 */
@Mixin(value = DawnstoneAnvil.BlacklistBreakdown.class, remap = false)
public interface DawnstoneAnvilBlacklistBreakdownAccessor {
    @Accessor
    Ingredient getMatch();
}
