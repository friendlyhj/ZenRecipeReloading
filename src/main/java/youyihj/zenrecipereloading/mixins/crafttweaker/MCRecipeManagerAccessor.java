package youyihj.zenrecipereloading.mixins.crafttweaker;


import crafttweaker.mc1120.recipes.MCRecipeManager;
import gnu.trove.set.TIntSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.HashSet;

@Mixin(value = MCRecipeManager.class, remap = false)
public interface MCRecipeManagerAccessor {
    @Accessor
    static TIntSet getUsedHashes() {
        throw new AssertionError();
    }

    @Accessor
    static HashSet<String> getUsedRecipeNames() {
        throw new AssertionError();
    }
}
