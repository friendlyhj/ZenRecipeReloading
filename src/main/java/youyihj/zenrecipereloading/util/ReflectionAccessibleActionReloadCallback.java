package youyihj.zenrecipereloading.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import crafttweaker.IAction;
import youyihj.zenutils.api.reload.ActionReloadCallback;

import java.lang.reflect.Field;

/**
 * @author youyihj
 */
public abstract class ReflectionAccessibleActionReloadCallback<T extends IAction> extends ActionReloadCallback<T> {
    private static final Table<Class<?>, String, Field> FIELD_CACHE = HashBasedTable.create();

    public ReflectionAccessibleActionReloadCallback(T action) {
        super(action);
    }

    @SuppressWarnings("unchecked")
    protected <U> U getActionField(String fieldName) {
        try {
            return (U) findField(fieldName).get(action);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private Field findField(String fieldName) throws NoSuchFieldException {
        Class<? extends IAction> actionClass = action.getClass();
        Field field = FIELD_CACHE.get(actionClass, fieldName);
        if (field == null) {
            for (Class<?> clazz = actionClass; clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException ignored) {

                }
            }
            if (field != null) {
                field.setAccessible(true);
                FIELD_CACHE.put(actionClass, fieldName, field);
            } else {
                //noinspection DataFlowIssue
                throw new NoSuchFieldException(fieldName + " does not exist in " + actionClass.getSimpleName() + " or any of its superclasses.");
            }
        }
        return field;
    }

}
