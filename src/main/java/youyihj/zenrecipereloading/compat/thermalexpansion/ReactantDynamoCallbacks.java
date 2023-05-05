package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.util.managers.dynamo.ReactantManager;
import crafttweaker.IAction;
import net.minecraftforge.fluids.Fluid;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class ReactantDynamoCallbacks {
    public static String getActionName(String path) {
        return "com.blamejared.compat.thermalexpansion.dynamos.ReactantDynamo$" + path;
    }

    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ReactantManager.removeReaction(getActionField("stack"), getActionField("fluid"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class AddElemental extends PrivateActionReloadCallback {

        public AddElemental(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            ReactantManager.removeElementalReaction(getActionField("stack"), getActionField("fluid"));
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        protected ReactantManager.Reaction reaction;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            reaction = ReactantManager.getReaction(getActionField("stack"), ((Fluid) getActionField("fluid")));
        }

        @Override
        public void undo() {
            if (reaction != null) {
                ReactantManager.addReaction(reaction.getReactant(), reaction.getFluid(), reaction.getEnergy());
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class RemoveElemental extends Remove {

        public RemoveElemental(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            if (reaction != null) {
                ReactantManager.addElementalReaction(reaction.getReactant(), reaction.getFluid(), reaction.getEnergy());
            }
        }
    }
}
