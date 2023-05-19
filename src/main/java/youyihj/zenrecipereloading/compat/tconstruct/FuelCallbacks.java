package youyihj.zenrecipereloading.compat.tconstruct;

import crafttweaker.IAction;
import net.minecraftforge.fluids.FluidStack;
import youyihj.zenrecipereloading.mixins.tconstruct.TinkerRegistryAccessor;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

public class FuelCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.tconstruct.Fuel$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            FluidStack fuel = getActionField("fuel");
            TinkerRegistryAccessor.getSmelteryFuels().remove(fuel);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
