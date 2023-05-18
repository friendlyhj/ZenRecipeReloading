package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import crafttweaker.IAction;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.ArrayList;

public class ExcavatorCallbacks {
    public static String getActionName(String path) {
        return "blusunrize.immersiveengineering.common.util.compat.crafttweaker.Excavator$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ExcavatorHandler.MineralMix mineral = getActionField("mineral");
            ExcavatorHandler.mineralList.remove(mineral);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ArrayList<ExcavatorHandler.MineralMix> mix = getActionField("mix");
            ArrayList<Integer> weight = getActionField("weight");
            if (mix.size() == weight.size()) {
                for (int i = 0; i < mix.size(); i++) {
                    ExcavatorHandler.mineralList.put(mix.get(i), weight.get(i));
                }
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}