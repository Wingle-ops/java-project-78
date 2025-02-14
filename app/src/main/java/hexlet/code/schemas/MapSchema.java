package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        Predicate<Map> isRequired = map -> !map.isEmpty();
        addCheck("isRequired", isRequired);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> mapSize = map -> map.size() == size;
        addCheck("mapSize", mapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> map) {
        Predicate<Map> isShape = ((value) -> {
            for (String key : map.keySet()) {
                if (!map.get(key).isValid(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
        addCheck("isShape", isShape);
        return this;
    }
}
