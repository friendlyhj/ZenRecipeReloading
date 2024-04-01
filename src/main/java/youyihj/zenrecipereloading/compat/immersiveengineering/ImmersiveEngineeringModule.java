package youyihj.zenrecipereloading.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import youyihj.zenrecipereloading.module.PlainModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

/**
 * @author youyihj
 */
public class ImmersiveEngineeringModule extends PlainModule {
    @Override
    public void registerReloadCallbacks() {
        PrivateActionReloadCallback.uncheckedRegister(AlloySmelterCallbacks.getActionName("Add"), AlloySmelterCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(AlloySmelterCallbacks.getActionName("Remove"), AlloySmelterCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(AlloySmelterCallbacks.getActionName("RemoveAll"), AlloySmelterCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(ArcFurnaceCallbacks.getActionName("Add"), ArcFurnaceCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(ArcFurnaceCallbacks.getActionName("Remove"), ArcFurnaceCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(ArcFurnaceCallbacks.getActionName("RemoveAll"), ArcFurnaceCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(BlastFurnaceCallbacks.getActionName("Add"), BlastFurnaceCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(BlastFurnaceCallbacks.getActionName("Remove"), BlastFurnaceCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(BlastFurnaceCallbacks.getActionName("RemoveAll"), BlastFurnaceCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(BlastFurnaceCallbacks.getActionName("AddFuel"), BlastFurnaceCallbacks.AddFuel::new);
        PrivateActionReloadCallback.uncheckedRegister(BlastFurnaceCallbacks.getActionName("RemoveFuel"), BlastFurnaceCallbacks.RemoveFuel::new);

        PrivateActionReloadCallback.uncheckedRegister(BlueprintCallbacks.getActionName("Add"), BlueprintCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(BlueprintCallbacks.getActionName("Remove"), BlueprintCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(BlueprintCallbacks.getActionName("RemoveAll"), BlueprintCallbacks.RemoveAll::new);

        PrivateActionReloadCallback.uncheckedRegister(BottlingMachineCallbacks.getActionName("Add"), BottlingMachineCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(BottlingMachineCallbacks.getActionName("Remove"), BottlingMachineCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(BottlingMachineCallbacks.getActionName("RemoveAll"), BottlingMachineCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(CokeOvenCallbacks.getActionName("Add"), CokeOvenCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CokeOvenCallbacks.getActionName("Remove"), CokeOvenCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CokeOvenCallbacks.getActionName("RemoveAll"), CokeOvenCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(CrusherCallbacks.getActionName("Add"), CrusherCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(CrusherCallbacks.getActionName("Remove"), CrusherCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CrusherCallbacks.getActionName("RemoveForInput"), CrusherCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(CrusherCallbacks.getActionName("RemoveAll"), CrusherCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(DieselCallbacks.getActionName("AddFuel"), DieselCallbacks.AddFuel::new);
        PrivateActionReloadCallback.uncheckedRegister(DieselCallbacks.getActionName("RemoveFuel"), DieselCallbacks.RemoveFuel::new);
        PrivateActionReloadCallback.uncheckedRegister(DieselCallbacks.getActionName("AddDrillFuel"), DieselCallbacks.AddDrillFuel::new);
        PrivateActionReloadCallback.uncheckedRegister(DieselCallbacks.getActionName("RemoveDrillFuel"), DieselCallbacks.RemoveDrillFuel::new);

        PrivateActionReloadCallback.uncheckedRegister(ExcavatorCallbacks.getActionName("AddMineral"), ExcavatorCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(ExcavatorCallbacks.getActionName("RemoveMineral"), ExcavatorCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(FermenterCallbacks.getActionName("Add"), FermenterCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(FermenterCallbacks.getActionName("RemoveFluid"), FermenterCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(FermenterCallbacks.getActionName("RemoveStack"), FermenterCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(FermenterCallbacks.getActionName("RemoveByInput"), FermenterCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(FermenterCallbacks.getActionName("RemoveAll"), FermenterCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(MetalPressCallbacks.getActionName("Add"), MetalPressCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(MetalPressCallbacks.getActionName("Remove"), MetalPressCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(MetalPressCallbacks.getActionName("RemoveByMold"), MetalPressCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(MetalPressCallbacks.getActionName("RemoveAll"), MetalPressCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(MixerCallbacks.getActionName("Add"), MixerCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(MixerCallbacks.getActionName("RemoveFluid"), MixerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(MixerCallbacks.getActionName("RemoveAll"), MixerCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("Add"), RefineryCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("Remove"), RefineryCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(RefineryCallbacks.getActionName("RemoveAll"), RefineryCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(SqueezerCallbacks.getActionName("Add"), SqueezerCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(SqueezerCallbacks.getActionName("RemoveFluid"), SqueezerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(SqueezerCallbacks.getActionName("RemoveStack"), SqueezerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(SqueezerCallbacks.getActionName("RemoveByInput"), SqueezerCallbacks.Remove::new);
        PrivateActionReloadCallback.uncheckedRegister(SqueezerCallbacks.getActionName("RemoveAll"), SqueezerCallbacks.Remove::new);

        PrivateActionReloadCallback.uncheckedRegister(ThermoelectricCallbacks.getActionName("Add"), ThermoelectricCallbacks.Add::new);
        PrivateActionReloadCallback.uncheckedRegister(ThermoelectricCallbacks.getActionName("Remove"), ThermoelectricCallbacks.Remove::new);
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        ExcavatorHandler.recalculateChances(true);
    }
}
