package youyihj.zenrecipereloading.compat.embers;

import crafttweaker.api.minecraft.CraftTweakerMC;
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.api.misc.ICoefficientFuel;
import teamroots.embers.api.misc.IFuel;
import teamroots.embers.api.misc.ILiquidFuel;
import teamroots.embers.compat.crafttweaker.EmberGeneration;
import youyihj.zenrecipereloading.mixins.embers.*;
import youyihj.zenrecipereloading.util.MixinAccessibleActionReloadCallback;

/**
 * @author youyihj
 */
public class EmberGenerationCallbacks {
    public static class RemoveSteamEngineFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.RemoveSteamEngineFuel, RemoveSteamEngineFuelAccessor> {
        private ILiquidFuel fuel;

        public RemoveSteamEngineFuel(EmberGeneration.RemoveSteamEngineFuel action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            fuel = EmbersAPI.getSteamEngineFuel(CraftTweakerMC.getLiquidStack(getActionAccessor().getLiquid()));
        }

        @Override
        public void undo() {
            EmbersAPI.registerSteamEngineFuel(fuel);
        }
    }

    public static class AddSteamEngineFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.AddSteamEngineFuel, AddSteamEngineFuelAccessor> {

        public AddSteamEngineFuel(EmberGeneration.AddSteamEngineFuel action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterBoilerFluid(EmbersAPI.getSteamEngineFuel(CraftTweakerMC.getLiquidStack(getActionAccessor().getLiquid())));
        }
    }

    public static class RemoveBoilerFluid extends MixinAccessibleActionReloadCallback<EmberGeneration.RemoveBoilerFluid, RemoveBoilerFluidAccessor> {
        private ILiquidFuel fuel;

        public RemoveBoilerFluid(EmberGeneration.RemoveBoilerFluid action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            fuel = EmbersAPI.getBoilerFluid(CraftTweakerMC.getLiquidStack(getActionAccessor().getLiquid()));
        }

        @Override
        public void undo() {
            EmbersAPI.registerBoilerFluid(fuel);
        }
    }

    public static class AddBoilerFluid extends MixinAccessibleActionReloadCallback<EmberGeneration.AddBoilerFluid, AddBoilerFluidAccessor> {

        public AddBoilerFluid(EmberGeneration.AddBoilerFluid action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterBoilerFluid(EmbersAPI.getBoilerFluid(CraftTweakerMC.getLiquidStack(getActionAccessor().getLiquid())));
        }
    }

    public static class AddMetalCoefficient extends MixinAccessibleActionReloadCallback<EmberGeneration.AddMetalCoefficient, AddMetalCoefficientAccessor> {

        public AddMetalCoefficient(EmberGeneration.AddMetalCoefficient action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterMetalCoefficient(getActionAccessor().getMetalCoefficient());
        }
    }

    public static class RemoveCombustionFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.RemoveCombustionFuel, RemoveCombustionFuelAccessor> {
        private ICoefficientFuel fuel;

        public RemoveCombustionFuel(EmberGeneration.RemoveCombustionFuel action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            fuel = EmbersAPI.getCombustionFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem()));
        }

        @Override
        public void undo() {
            EmbersAPI.registerCombustionFuel(fuel);
        }
    }

    public static class AddCombustionFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.AddCombustionFuel, AddCombustionFuelAccessor> {

        public AddCombustionFuel(EmberGeneration.AddCombustionFuel action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterCombustionFuel(EmbersAPI.getCombustionFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem())));
        }
    }

    public static class RemoveCatalysisFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.RemoveCatalysisFuel, RemoveCatalysisFuelAccessor> {
        private ICoefficientFuel fuel;

        public RemoveCatalysisFuel(EmberGeneration.RemoveCatalysisFuel action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            fuel = EmbersAPI.getCatalysisFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem()));
        }

        @Override
        public void undo() {
            EmbersAPI.registerCatalysisFuel(fuel);
        }
    }

    public static class AddCatalysisFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.AddCatalysisFuel, AddCatalysisFuelAccessor> {

        public AddCatalysisFuel(EmberGeneration.AddCatalysisFuel action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterCatalysisFuel(EmbersAPI.getCatalysisFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem())));
        }
    }

    public static class RemoveEmberFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.RemoveEmberFuel, RemoveEmberFuelAccessor> {
        private IFuel fuel;

        public RemoveEmberFuel(EmberGeneration.RemoveEmberFuel action) {
            super(action);
        }

        @Override
        public void beforeApply(boolean reload) {
            fuel = EmbersAPI.getEmberFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem()));
        }

        @Override
        public void undo() {
            EmbersAPI.registerEmberFuel(fuel);
        }
    }

    public static class AddEmberFuel extends MixinAccessibleActionReloadCallback<EmberGeneration.AddEmberFuel, AddEmberFuelAccessor> {

        public AddEmberFuel(EmberGeneration.AddEmberFuel action) {
            super(action);
        }

        @Override
        public void undo() {
            EmbersAPI.unregisterEmberFuel(EmbersAPI.getEmberFuel(CraftTweakerMC.getItemStack(getActionAccessor().getItem())));
        }
    }
}
