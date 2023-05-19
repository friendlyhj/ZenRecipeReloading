package youyihj.zenrecipereloading.compat.tconstruct;

import com.blamejared.compat.tconstruct.Alloy;
import com.blamejared.compat.tconstruct.Casting;
import com.blamejared.compat.tconstruct.Drying;
import com.blamejared.compat.tconstruct.Melting;
import com.blamejared.compat.tconstruct.actions.*;
import com.blamejared.compat.tconstruct.recipes.AlloyRecipeTweaker;
import com.blamejared.compat.tconstruct.recipes.CastingRecipeTweaker;
import com.blamejared.compat.tconstruct.recipes.DryingRecipeTweaker;
import com.blamejared.compat.tconstruct.recipes.MeltingRecipeTweaker;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;
import slimeknights.tconstruct.library.DryingRecipe;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.smeltery.AlloyRecipe;
import slimeknights.tconstruct.library.smeltery.ICastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import youyihj.zenrecipereloading.mixins.tconstruct.TinkerRegistryAccessor;
import youyihj.zenrecipereloading.module.IModule;
import youyihj.zenrecipereloading.util.PrivateActionReloadCallback;
import youyihj.zenrecipereloading.util.RuntimePrivateActionReloadCallback;
import youyihj.zenrecipereloading.util.RuntimeReloadCallback;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public enum TconstructModule implements IModule {
    INSTANCE;

    public final List<MeltingRecipe> originalMeltingRegistry = Lists.newArrayList();
    public final List<ICastingRecipe> originalTableCastRegistry = Lists.newArrayList();
    public final List<ICastingRecipe> originalBasinCastRegistry = Lists.newArrayList();
    public final List<AlloyRecipe> originalAlloyRegistry = Lists.newArrayList();
    public final List<DryingRecipe> originalDryingRegistry = Lists.newArrayList();
    public final List<Pair<Class<? extends Entity>, FluidStack>> originalEntityMeltingRegistry = Lists.newArrayList();


    @Override
    public void registerReloadCallbacks() {
        PrivateActionReloadCallback.Factory runtimeFactory = RuntimePrivateActionReloadCallback::new;
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Alloy$Add", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Alloy$Remove", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Casting$Remove", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Casting$Add", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Melting$Add", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Melting$Remove", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Drying$Add", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Drying$Remove", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Melting$AddEntityMelting", runtimeFactory);
        PrivateActionReloadCallback.uncheckedRegister("com.blamejared.compat.tconstruct.Melting$RemoveEntityMelting", runtimeFactory);

        PrivateActionReloadCallback.uncheckedRegister(FuelCallbacks.getActionName("Add"), FuelCallbacks.Add::new);

        IActionReloadCallbackFactory.register(SetAttackAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetBowBonusDamageAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetBowRangeAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetDrawSpeedAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetDurabilityAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetHarvestLevelAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetMiningSpeedAction.class, RuntimeReloadCallback::new);
        IActionReloadCallbackFactory.register(SetModifierAction.class, RuntimeReloadCallback::new);
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        Alloy.REMOVED_RECIPES.clear();
        Casting.REMOVED_RECIPES_BASIN.clear();
        Casting.REMOVED_RECIPES_TABLE.clear();
        Melting.REMOVED_RECIPES.clear();
        Drying.DRYING_RECIPES.clear();
        Melting.ADDED_ENTITIES.clear();
        Melting.REMOVED_ENTITIES.clear();
        TinkerRegistryAccessor.getMeltingRegistry().clear();
        TinkerRegistryAccessor.getBasinCastRegistry().clear();
        TinkerRegistryAccessor.getTableCastRegistry().clear();
        TinkerRegistryAccessor.getAlloyRegistry().clear();
        TinkerRegistryAccessor.getDryingRegistry().clear();
        TinkerRegistryAccessor.getEntityMeltingRegistry().clear();
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        copiedForeach(originalMeltingRegistry, TinkerRegistry::registerMelting);
        copiedForeach(originalBasinCastRegistry, TinkerRegistry::registerBasinCasting);
        copiedForeach(originalTableCastRegistry, TinkerRegistry::registerTableCasting);
        copiedForeach(originalAlloyRegistry, TinkerRegistry::registerAlloy);
        copiedForeach(originalDryingRegistry, TinkerRegistry::addDryingRecipe);
        copiedForeach(originalEntityMeltingRegistry, pair -> TinkerRegistry.registerEntityMelting(pair.getKey(), pair.getRight()));
    }

    private <T> void copiedForeach(List<T> list, Consumer<? super T> action) {
        List<T> copy = new ArrayList<>(list);
        list.clear();
        copy.forEach(action);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordAlloyRecipe(TinkerRegisterEvent.AlloyRegisterEvent event) {
        if (!(event.getRecipe() instanceof AlloyRecipeTweaker)) {
            originalAlloyRegistry.add(event.getRecipe());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordTableCastRecipe(TinkerRegisterEvent.TableCastingRegisterEvent event) {
        if (!(event.getRecipe() instanceof CastingRecipeTweaker)) {
            originalTableCastRegistry.add(event.getRecipe());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordBasinCastRecipe(TinkerRegisterEvent.BasinCastingRegisterEvent event) {
        if (!(event.getRecipe() instanceof CastingRecipeTweaker)) {
            originalBasinCastRegistry.add(event.getRecipe());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordMeltingRecipe(TinkerRegisterEvent.MeltingRegisterEvent event) {
        if (!(event.getRecipe() instanceof MeltingRecipeTweaker)) {
            originalMeltingRegistry.add(event.getRecipe());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordMeltingRecipe(TinkerRegisterEvent.DryingRackRegisterEvent event) {
        if (!(event.getRecipe() instanceof DryingRecipeTweaker)) {
            originalDryingRegistry.add(event.getRecipe());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void recordEntityMeltingRecipe(TinkerRegisterEvent.EntityMeltingRegisterEvent event) {
        boolean isCrT = false;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if ("apply".equals(stackTraceElement.getMethodName())) {
                isCrT = true;
                break;
            }
        }
        if (!isCrT) {
            originalEntityMeltingRegistry.add(Pair.of(event.getRecipe(), event.getNewFluidStack()));
        }
    }

}
