package youyihj.zenrecipereloading.compat.botania;

import crafttweaker.IAction;
import vazkii.botania.api.BotaniaAPI;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class OrechidCallbacks {
    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            String oreDict = getActionField("oreDict");
            BotaniaAPI.oreWeights.remove(oreDict);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private Integer prev;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            String oreDict = getActionField("oreDict");
            prev = BotaniaAPI.oreWeights.get(oreDict);
        }

        @Override
        public void undo() {
            if (prev != null) {
                String oreDict = getActionField("oreDict");
                BotaniaAPI.addOreWeight(oreDict, prev);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
