package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

///**
//* @param <T> Тип, которым типизируется класс наследник
// */

public class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected final void addCheck(String check, Predicate rule) {
        checks.put(check, rule);
    }

    public final boolean isValid(Object data) {
        if (checks.containsKey("isRequired") && data == null) {
            return false;
        }
        if (data != null) {
            for (String check : checks.keySet()) {
                if (!checks.get(check).test((T) data)) {
                    return false;
                }
            }
        }
        return true;
    }
}
