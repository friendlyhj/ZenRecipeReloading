package youyihj.zenrecipereloading.compat.thermalexpansion;

import cofh.thermalexpansion.ThermalExpansion;
import com.google.common.base.Suppliers;
import youyihj.zenrecipereloading.ZenRecipeReloading;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * @author youyihj
 */
public class ThermalExpansionModule implements IModule {
    private static final Supplier<Method> REFRESH_MANAGERS_METHOD = Suppliers.memoize(() -> {
        try {
            Method method = ThermalExpansion.class.getDeclaredMethod("refreshManagers");
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });

    @Override
    public void registerReloadCallbacks() {
        PrivateActionReloadCallback.uncheckedRegister(CentrifugeCallbacks.getActionName("Add"), CentrifugeCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CentrifugeCallbacks.getActionName("Remove"), CentrifugeCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CentrifugeCallbacks.getActionName("AddMob"), CentrifugeCallbacks.AddMob::new);
        PrivateActionReloadCallback.uncheckedRegister(CentrifugeCallbacks.getActionName("RemoveMob"), CentrifugeCallbacks.RemoveMob::new);
        PrivateActionReloadCallback.uncheckedRegister(CompactorCallbacks.getActionName("Add"), CompactorCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CompactorCallbacks.getActionName("Remove"), CompactorCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CrucibleCallbacks.getActionName("Add"), CrucibleCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CrucibleCallbacks.getActionName("Remove"), CrucibleCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(EnchanterCallbacks.getActionName("Add"), EnchanterCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(EnchanterCallbacks.getActionName("Remove"), CompactorCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(ImbuerCallbacks.getActonName("Add"), ImbuerCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(ImbuerCallbacks.getActonName("Remove"), ImbuerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(InductionSmelterCallbacks.getActionName("Add"), InductionSmelterCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(InductionSmelterCallbacks.getActionName("Remove"), InductionSmelterCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(InfuserCallbacks.getActonName("Add"), InfuserCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(InfuserCallbacks.getActonName("Remove"), InfuserCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(InsolatorCallbacks.getActionName("Add"), InsolatorCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(InsolatorCallbacks.getActionName("Remove"), InsolatorCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(PulverizerCallbacks.getActionName("Add"), PulverizerCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(PulverizerCallbacks.getActionName("Remove"), PulverizerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(RedstoneFurnaceCallbacks.getActionName("Add"), RedstoneFurnaceCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(RedstoneFurnaceCallbacks.getActionName("Remove"), RedstoneFurnaceCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(RedstoneFurnaceCallbacks.getActionName("AddPyrolysis"), RedstoneFurnaceCallbacks.AddPyrolysis::new);
        PrivateActionReloadCallback.uncheckedRegister(RedstoneFurnaceCallbacks.getActionName("RemovePyrolysis"), RedstoneFurnaceCallbacks.RemovePyrolysis::new);
        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("Add"), action -> new RefineryCallbacks.Add(action, false));
        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("AddPotion"), action -> new RefineryCallbacks.Add(action, true));
        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("Remove"), RefineryCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(SawMillCallbacks.getActionName("Add"), SawMillCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(SawMillCallbacks.getActionName("Remove"), SawMillCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(TransposerCallbacks.getActionName("AddExtract"), TransposerCallbacks.AddExtract::new);
        PrivateActionReloadCallback.uncheckedRegister(TransposerCallbacks.getActionName("AddFill"), TransposerCallbacks.AddFill::new);
        PrivateActionReloadCallback.uncheckedRegister(TransposerCallbacks.getActionName("RemoveExtract"), TransposerCallbacks.RemoveExtract::new);
        PrivateActionReloadCallback.uncheckedRegister(TransposerCallbacks.getActionName("RemoveFill"), TransposerCallbacks.RemoveFill::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {

    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        try {
            REFRESH_MANAGERS_METHOD.get().invoke(ThermalExpansion.instance);
        } catch (Throwable e) {
            ZenRecipeReloading.logger.catching(e);
        }
    }
}
