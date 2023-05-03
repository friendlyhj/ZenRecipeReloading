package youyihj.zenrecipereloading.module;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import youyihj.zenutils.api.reload.IActionReloadCallbackFactory;
import youyihj.zenutils.api.reload.ScriptReloadEvent;

import java.util.*;

/**
 * @author youyihj
 */
public class PlainModule implements IModule {
    private final Map<Class<?>, IActionReloadCallbackFactory<?>> callbackFactories = new HashMap<>();
    private final List<Collection<? extends IAction>> trackActions = new ArrayList<>();

    @Override
    public void registerReloadCallbacks() {
        registerReloadCallbacks0();
    }

    public <T extends IAction> void addCallbackFactory(Class<T> clazz, IActionReloadCallbackFactory<T> callbackFactory) {
        callbackFactories.put(clazz, callbackFactory);
    }

    public void trackActions(Collection<? extends IAction> actions) {
        trackActions.add(actions);
    }

    @SuppressWarnings("unchecked")
    private <T extends IAction> void registerReloadCallbacks0() {
        callbackFactories.forEach((clazz, factory) -> {
            Class<T> tClass = (Class<T>) clazz;
            IActionReloadCallbackFactory<T> callbackFactory = (IActionReloadCallbackFactory<T>) factory;
            IActionReloadCallbackFactory.register(tClass, callbackFactory);
        });
    }

    @Override
    public void onReloadPre(ScriptReloadEvent.Pre event) {
        trackActions.forEach(Collection::clear);
    }

    @Override
    public void onReloadPost(ScriptReloadEvent.Post event) {
        trackActions.forEach(it -> it.forEach(CraftTweakerAPI::apply));
    }
}
