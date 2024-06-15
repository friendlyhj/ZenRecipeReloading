package youyihj.zenrecipereloading.compat.embers;

import teamroots.embers.compat.crafttweaker.*;
import youyihj.zenrecipereloading.module.PlainModule;

/**
 * @author youyihj
 */
public class EmbersModule extends PlainModule {
    public EmbersModule() {
        addCallbackFactory(Alchemy.Add.class, AlchemyCallbacks.Add::new);
        addCallbackFactory(Alchemy.RemoveByOutput.class, AlchemyCallbacks.Remove::new);
        addCallbackFactory(Alchemy.RemoveAll.class, AlchemyCallbacks.RemoveAll::new);

        addCallbackFactory(DawnstoneAnvil.Add.class, DawnstoneAnvilCallbacks.Add::new);
        addCallbackFactory(DawnstoneAnvil.RemoveByInput.class, DawnstoneAnvilCallbacks.Remove::new);
        addCallbackFactory(DawnstoneAnvil.BlacklistBreakdown.class, DawnstoneAnvilCallbacks.BlacklistBreakdown::new);
        addCallbackFactory(DawnstoneAnvil.BlacklistMateria.class, DawnstoneAnvilCallbacks.BlacklistMaterial::new);
        addCallbackFactory(DawnstoneAnvil.BlacklistRepair.class, DawnstoneAnvilCallbacks.BlacklistRepair::new);

        addCallbackFactory(EmberGeneration.AddBoilerFluid.class, EmberGenerationCallbacks.AddBoilerFluid::new);
        addCallbackFactory(EmberGeneration.RemoveBoilerFluid.class, EmberGenerationCallbacks.RemoveBoilerFluid::new);
        addCallbackFactory(EmberGeneration.AddCombustionFuel.class, EmberGenerationCallbacks.AddCombustionFuel::new);
        addCallbackFactory(EmberGeneration.RemoveCombustionFuel.class, EmberGenerationCallbacks.RemoveCombustionFuel::new);
        addCallbackFactory(EmberGeneration.AddMetalCoefficient.class, EmberGenerationCallbacks.AddMetalCoefficient::new);
        addCallbackFactory(EmberGeneration.AddCatalysisFuel.class, EmberGenerationCallbacks.AddCatalysisFuel::new);
        addCallbackFactory(EmberGeneration.RemoveCatalysisFuel.class, EmberGenerationCallbacks.RemoveCatalysisFuel::new);
        addCallbackFactory(EmberGeneration.AddSteamEngineFuel.class, EmberGenerationCallbacks.AddSteamEngineFuel::new);
        addCallbackFactory(EmberGeneration.RemoveSteamEngineFuel.class, EmberGenerationCallbacks.RemoveSteamEngineFuel::new);
        addCallbackFactory(EmberGeneration.AddEmberFuel.class, EmberGenerationCallbacks.AddEmberFuel::new);
        addCallbackFactory(EmberGeneration.RemoveEmberFuel.class, EmberGenerationCallbacks.RemoveEmberFuel::new);

        addCallbackFactory(HeatCoil.RemoveAll.class, HeatCoilCallbacks.RemoveAll::new);
        addCallbackFactory(HeatCoil.RemoveByInput.class, HeatCoilCallbacks.RemoveByInput::new);
        addCallbackFactory(HeatCoil.Add.class, HeatCoilCallbacks.Add::new);

        addCallbackFactory(Melter.RemoveAll.class, MelterCallbacks.RemoveAll::new);
        addCallbackFactory(Melter.RemoveByInput.class, MelterCallbacks.RemoveByInput::new);
        addCallbackFactory(Melter.RemoveByOutput.class, MelterCallbacks.RemoveByOutput::new);
        addCallbackFactory(Melter.Add.class, MelterCallbacks.Add::new);

        addCallbackFactory(Mixer.RemoveAll.class, MixerCallbacks.RemoveAll::new);
        addCallbackFactory(Mixer.RemoveByOutput.class, MixerCallbacks.RemoveByOutput::new);
        addCallbackFactory(Mixer.Add.class, MixerCallbacks.Add::new);

        addCallbackFactory(Stamper.RemoveAll.class, StamperCallbacks.RemoveAll::new);
        addCallbackFactory(Stamper.RemoveByOutput.class, StamperCallbacks.RemoveByOutput::new);
        addCallbackFactory(Stamper.Add.class, StamperCallbacks.Add::new);
    }
}
