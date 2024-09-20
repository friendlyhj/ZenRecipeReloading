package youyihj.zenrecipereloading.compat.vanilla;

import crafttweaker.IAction;
import crafttweaker.api.formatting.IFormattedText;
import crafttweaker.api.tooltip.ITooltipFunction;
import crafttweaker.api.util.IngredientMap;
import stanhebben.zenscript.util.Pair;
import youyihj.zenrecipereloading.mixins.crafttweaker.IngredientTooltipsAccessor;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.regex.Pattern;

public class TooltipCallbacks {
    public static String getActionName(String path) {
        return "crafttweaker.api.tooltip.IngredientTooltips$" + path;
    }

    public static class AddTooltipAction extends PrivateActionReloadCallback {
        public AddTooltipAction(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            // (shift ? SHIFT_TOOLTIPS : TOOLTIPS).register(ingredient, new Pair<>(tooltip, showMessage));
            boolean shift = getActionField("shift");
            IngredientMap.IngredientMapEntry<Pair<IFormattedText, IFormattedText>> entry = new IngredientMap.IngredientMapEntry<>(
                    getActionField("ingredient"),
                    new Pair<>(getActionField("tooltip"), getActionField("showMessage"))
            );
            (shift ? IngredientTooltipsAccessor.getShiftTooltips() : IngredientTooltipsAccessor.getTooltips()).unregister(entry);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveTooltipAction extends PrivateActionReloadCallback {
        public RemoveTooltipAction(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            //  REMOVED_TOOLTIPS.register(ingredient, Pattern.compile(regex));
            IngredientMap.IngredientMapEntry<Pattern> entry = new IngredientMap.IngredientMapEntry<>(
                    getActionField("ingredient"),
                    Pattern.compile(getActionField("regex"))
            );
            IngredientTooltipsAccessor.getRemovedTooltips().unregister(entry);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddAdvancedTooltipAction extends PrivateActionReloadCallback {
        public AddAdvancedTooltipAction(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            // (shift ? SHIFT_TOOLTIP_FUNCTIONS : TOOLTIP_FUNCTIONS).register(ingredient, new Pair<>(function, showMessage));
            boolean shift = getActionField("shift");
            IngredientMap.IngredientMapEntry<Pair<ITooltipFunction, ITooltipFunction>> entry = new IngredientMap.IngredientMapEntry<>(
                    getActionField("ingredient"),
                    new Pair<>(getActionField("function"), getActionField("showMessage"))
            );
            (shift ? IngredientTooltipsAccessor.getShiftTooltipFunctions() : IngredientTooltipsAccessor.getTooltipFunctions()).unregister(entry);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class ClearTooltipAction extends PrivateActionReloadCallback {
        public ClearTooltipAction(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            // CLEARED_TOOLTIPS.register(ingredient, leaveName);
            IngredientMap.IngredientMapEntry<Boolean> entry = new IngredientMap.IngredientMapEntry<>(
                    getActionField("ingredient"),
                    getActionField("leaveName")
            );
            IngredientTooltipsAccessor.getClearedTooltips().unregister(entry);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveTooltipLineAction extends PrivateActionReloadCallback {
        public RemoveTooltipLineAction(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            // REMOVED_TOOLTIPS_LINE.register(ingredient, line);
            IngredientMap.IngredientMapEntry<Integer> entry = new IngredientMap.IngredientMapEntry<>(
                    getActionField("ingredient"),
                    getActionField("line")
            );
            IngredientTooltipsAccessor.getRemovedTooltipsLine().unregister(entry);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
