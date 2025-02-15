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

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        Predicate<Map> shape = map -> {
            if (map == null) {
                return false;
            }
            return schemas.entrySet().stream().allMatch(schema ->
                    map.containsKey(schema.getKey()) && schema.getValue().isValid(map.get(schema.getKey()))
            );
        };
        addCheck("shape", shape);
        return this;
    }
}
