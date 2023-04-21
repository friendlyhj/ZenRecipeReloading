package youyihj.zenrecipereloading.compat.vanilla;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.item.crafting.IRecipe;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author youyihj
 */
public interface ActionBaseRemoveRecipesAccessor {
    Int2ObjectArrayMap<IRecipe> getRemovedRecipes();
}
