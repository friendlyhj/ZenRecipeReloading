package youyihj.zenrecipereloading.compat.avaritia;

import morph.avaritia.compat.crafttweaker.AddRecipeAction;
import morph.avaritia.compat.crafttweaker.RemoveRecipeAction;
import youyihj.zenrecipereloading.module.PlainModule;

/**
 * @author youyihj
 */
public class AvaritiaModule extends PlainModule {
    public AvaritiaModule() {
        addCallbackFactory(AddRecipeAction.class, AvaritiaCallbacks.Add::new);
        addCallbackFactory(RemoveRecipeAction.class, AvaritiaCallbacks.Remove::new);
    }
}
