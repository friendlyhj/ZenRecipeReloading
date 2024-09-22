package youyihj.zenrecipereloading.mixins.crafttweaker;


import crafttweaker.api.formatting.IFormattedText;
import crafttweaker.api.tooltip.ITooltipFunction;
import crafttweaker.api.tooltip.IngredientTooltips;
import crafttweaker.api.util.IngredientMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import stanhebben.zenscript.util.Pair;

import java.util.regex.Pattern;

@Mixin(value = IngredientTooltips.class, remap = false)
public interface IngredientTooltipsAccessor {
    @Accessor("TOOLTIPS")
    static IngredientMap<Pair<IFormattedText, IFormattedText>> getTooltips() {
        throw new AssertionError();
    }

    @Accessor("SHIFT_TOOLTIPS")
    static IngredientMap<Pair<IFormattedText, IFormattedText>> getShiftTooltips() {
        throw new AssertionError();
    }

    @Accessor("CLEARED_TOOLTIPS")
    static IngredientMap<Boolean> getClearedTooltips() {
        throw new AssertionError();
    }

    @Accessor("TOOLTIP_FUNCTIONS")
    static IngredientMap<Pair<ITooltipFunction, ITooltipFunction>> getTooltipFunctions() {
        throw new AssertionError();
    }

    @Accessor("SHIFT_TOOLTIP_FUNCTIONS")
    static IngredientMap<Pair<ITooltipFunction, ITooltipFunction>> getShiftTooltipFunctions() {
        throw new AssertionError();
    }

    @Accessor("REMOVED_TOOLTIPS")
    static IngredientMap<Pattern> getRemovedTooltips() {
        throw new AssertionError();
    }

    @Accessor("REMOVED_TOOLTIPS_LINE")
    static IngredientMap<Integer> getRemovedTooltipsLine() {
        throw new AssertionError();
    }
}
