package youyihj.zenrecipereloading.mixins.thaumcraft;

import com.blamejared.compat.thaumcraft.handlers.handlers.ArcaneWorkbench;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.crafting.IArcaneRecipe;
import youyihj.zenrecipereloading.compat.thaumcraft.ArcaneWorkbenchRemoveAccessor;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
@Mixin(value = ArcaneWorkbench.Remove.class, remap = false)
public abstract class MixinArcaneWorkbenchRemoveAction implements ArcaneWorkbenchRemoveAccessor {
    @Unique
    private List<IArcaneRecipe> removedRecipes;

    @WrapOperation(method = "apply", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private void recordRemoveRecipes(List<ResourceLocation> instance, Consumer<ResourceLocation> consumer, Operation<Void> original) {
        removedRecipes = instance.stream()
                .map(it -> RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).getValue(it))
                .filter(IArcaneRecipe.class::isInstance)
                .map(IArcaneRecipe.class::cast)
                .collect(Collectors.toList());
        original.call(instance, consumer);
    }

    @Override
    public List<IArcaneRecipe> getRemoved() {
        return removedRecipes;
    }
}
