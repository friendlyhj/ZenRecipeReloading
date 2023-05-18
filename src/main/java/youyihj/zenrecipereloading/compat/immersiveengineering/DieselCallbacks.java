package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.energy.DieselHandler;
import crafttweaker.IAction;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraftforge.fluids.FluidRegistry;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

public class DieselCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.DieselHelper$" + path;
    }

    public static class AddFuel extends PrivateActionReloadCallback {

        public AddFuel(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ILiquidStack fuel = getActionField("fuel");
            DieselHandler.removeFuel(FluidRegistry.getFluid(fuel.getName()));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveFuel extends PrivateActionReloadCallback {

        private int time;

        public RemoveFuel(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            ILiquidStack fuel = getActionField("fuel");
            time = DieselHandler.getBurnTime(FluidRegistry.getFluid(fuel.getName()));
        }

        @Override
        public void undo() {
            if (time != 0) {
                ILiquidStack fuel = getActionField("fuel");
                DieselHandler.registerFuel(FluidRegistry.getFluid(fuel.getName()), time);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddDrillFuel extends PrivateActionReloadCallback {

        public AddDrillFuel(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ILiquidStack fuel = getActionField("fuel");
            DieselHandler.removeDrillFuel(FluidRegistry.getFluid(fuel.getName()));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveDrillFuel extends PrivateActionReloadCallback {

        public RemoveDrillFuel(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ILiquidStack fuel = getActionField("fuel");
            DieselHandler.registerDrillFuel(FluidRegistry.getFluid(fuel.getName()));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}