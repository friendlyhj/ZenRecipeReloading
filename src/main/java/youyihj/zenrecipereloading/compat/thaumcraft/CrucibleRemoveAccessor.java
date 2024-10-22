package youyihj.zenrecipereloading.compat.thaumcraft;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.crafting.IThaumcraftRecipe;

import java.util.Map;

/**
 * @author youyihj
 */
public interface CrucibleRemoveAccessor {
    Map<ResourceLocation, IThaumcraftRecipe> getRemoved();
}
