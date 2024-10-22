package youyihj.zenrecipereloading.mixins.thaumcraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.crafting.IThaumcraftRecipe;
import thaumcraft.api.internal.CommonInternals;
import youyihj.zenutils.api.util.ReflectionInvoked;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
@Mixin(targets = "com.blamejared.compat.thaumcraft.handlers.handlers.Infusion$Remove", remap = false)
public abstract class MixinInfusionRemoveAction {
    @ReflectionInvoked
    public Map<ResourceLocation, IThaumcraftRecipe> removedRecipes;

    @WrapOperation(method = "apply", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private void recordRemoveRecipes(List<ResourceLocation> instance, Consumer<ResourceLocation> consumer, Operation<Void> original) {
        removedRecipes = instance.stream().collect(Collectors.toMap(
                Function.identity(),
                CommonInternals.craftingRecipeCatalog::get
        ));
        original.call(instance, consumer);
    }
}
