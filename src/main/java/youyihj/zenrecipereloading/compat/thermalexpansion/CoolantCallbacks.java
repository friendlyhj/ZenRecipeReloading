package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.device.CoolantManager;
import com.blamejared.mtlib.helpers.InputHelper;
import crafttweaker.IAction;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraftforge.fluids.Fluid;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class CoolantCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.devicemanagers.Coolant$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ILiquidStack fluid = getActionField("fluid");
            CoolantManager.removeCoolant(fluid.getDefinition().getName());
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private int rf, factor;
        private boolean valid;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            Fluid fluid = InputHelper.getFluid(getActionField("liquid"));
            rf = CoolantManager.getCoolantRF(fluid);
            factor = CoolantManager.getCoolantFactor(fluid);
            valid = CoolantManager.isValidCoolant(fluid);
        }

        @Override
        public void undo() {
            if (valid) {
                ILiquidStack fluid = getActionField("liquid");
                CoolantManager.addCoolant(fluid.getDefinition().getName(), rf, factor);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
