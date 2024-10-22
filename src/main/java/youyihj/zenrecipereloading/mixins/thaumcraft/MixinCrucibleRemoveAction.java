package youyihj.zenrecipereloading.mixins.thaumcraft;

import com.blamejared.compat.thaumcraft.handlers.handlers.Crucible;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.crafting.IThaumcraftRecipe;
import thaumcraft.api.internal.CommonInternals;
import youyihj.zenrecipereloading.compat.thaumcraft.CrucibleRemoveAccessor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author youyihj
 */
@Mixin(value = Crucible.Remove.class, remap = false)
public abstract class MixinCrucibleRemoveAction implements CrucibleRemoveAccessor {
    @Unique
    private Map<ResourceLocation, IThaumcraftRecipe> removedRecipes;

    @WrapOperation(method = "apply", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private void recordRemoveRecipes(List<ResourceLocation> instance, Consumer<ResourceLocation> consumer, Operation<Void> original) {
        removedRecipes = instance.stream().collect(Collectors.toMap(
                Function.identity(),
                CommonInternals.craftingRecipeCatalog::get
        ));
        original.call(instance, consumer);
    }

    @Override
    public Map<ResourceLocation, IThaumcraftRecipe> getRemoved() {
        return removedRecipes;
    }
}
