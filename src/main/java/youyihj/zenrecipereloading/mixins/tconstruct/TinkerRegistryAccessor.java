package youyihj.zenrecipereloading.mixins.tconstruct;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import slimeknights.tconstruct.library.DryingRecipe;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.smeltery.AlloyRecipe;
import slimeknights.tconstruct.library.smeltery.ICastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import java.util.List;
import java.util.Map;

@Mixin(value = TinkerRegistry.class, remap = false)
public interface TinkerRegistryAccessor {
    @Accessor
    static List<MeltingRecipe> getMeltingRegistry() {
        return Lists.newArrayList();
    }

    @Accessor
    static List<ICastingRecipe> getTableCastRegistry() {
        return Lists.newArrayList();
    }

    @Accessor
    static List<ICastingRecipe> getBasinCastRegistry() {
        return Lists.newArrayList();
    }

    @Accessor
    static List<AlloyRecipe> getAlloyRegistry() {
        return Lists.newArrayList();
    }

    @Accessor
    static List<DryingRecipe> getDryingRegistry() {
        return Lists.newArrayList();
    }

    @Accessor
    static Map<FluidStack, Integer> getSmelteryFuels() {
        return Maps.newHashMap();
    }

    @Accessor
    static Map<ResourceLocation, FluidStack> getEntityMeltingRegistry() {
        return Maps.newHashMap();
    }
}
