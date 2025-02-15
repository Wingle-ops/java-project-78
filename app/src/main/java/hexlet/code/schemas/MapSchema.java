package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        required = false;
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> mapSize = map -> map.size() == size;
        addCheck("mapSize", mapSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> map) {
        Predicate<Map> isShape = ((value) -> {
            return map.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                return value.containsKey(key) && schema.isValid(value.get(key));
            });

        });
        addCheck("isShape", isShape);
        return this;
    }
}
