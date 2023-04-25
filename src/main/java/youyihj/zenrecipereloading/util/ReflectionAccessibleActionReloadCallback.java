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
        Class<? extends IAction> clazz = action.getClass();
        Field field = FIELD_CACHE.get(clazz, fieldName);
        if (field == null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                FIELD_CACHE.put(clazz, fieldName, field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return (U) field.get(action);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
