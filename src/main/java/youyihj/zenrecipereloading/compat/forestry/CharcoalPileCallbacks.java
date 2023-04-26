package youyihj.zenrecipereloading.compat.forestry;

import crafttweaker.IAction;
import forestry.api.arboriculture.ICharcoalPileWall;
import forestry.api.arboriculture.TreeManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

import java.util.Objects;

/**
 * @author youyihj
 */
public class CharcoalPileCallbacks {
    public static class Add extends PrivateActionReloadCallback {

        public Add(IAction action) {
            super(action);
        }

        @Override
        public void undo() {
            Block block = getActionField("block");
            IBlockState state = getActionField("state");
            if (block != null) {
                Objects.requireNonNull(TreeManager.charcoalManager).removeWall(block);
            }
            if (state != null) {
                Objects.requireNonNull(TreeManager.charcoalManager).removeWall(state);
            }
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }

    public static class Remove extends PrivateActionReloadCallback {

        private ICharcoalPileWall recipe;

        public Remove(IAction action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            IBlockState state = getActionField("state");
            if (state == null) {
                Block block = getActionField("block");
                state = block.getDefaultState();
            }
            for (ICharcoalPileWall wall : Objects.requireNonNull(TreeManager.charcoalManager).getWalls()) {
                if (wall.matches(state)) {
                    this.recipe = wall;
                    break;
                }
            }
        }

        @Override
        public void undo() {
            Objects.requireNonNull(TreeManager.charcoalManager).registerWall(recipe);
        }

        @Override
        public boolean hasUndoMethod() {
            return true;
        }
    }
}
