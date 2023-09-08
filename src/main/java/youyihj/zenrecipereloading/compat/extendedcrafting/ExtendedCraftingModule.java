package youyihj.zenrecipereloading.compat.extendedcrafting;

import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;

/**
 * @author youyihj
 */
public class ExtendedCraftingModule extends PlainModule {
    public ExtendedCraftingModule() {
        PrivateActionReloadCallback.uncheckedRegister(CombinationCraftingCallbacks.getActionName("Add"), CombinationCraftingCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CombinationCraftingCallbacks.getActionName("Remove"), CombinationCraftingCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CompressionCraftingCallbacks.getActionName("Add"), CompressionCraftingCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CompressionCraftingCallbacks.getActionName("Remove"), CompressionCraftingCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(EnderCraftingCallbacks.getActionName("Add"), EnderCraftingCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(EnderCraftingCallbacks.getActionName("Remove"), EnderCraftingCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(TableCraftingCallbacks.getActionName("Add"), TableCraftingCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(TableCraftingCallbacks.getActionName("Remove"), TableCraftingCallbacks.Remove::new);
    }
}
