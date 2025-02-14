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

    public <R> MapSchema shape(Map<String, BaseSchema<R>> schemas) {
        Predicate<Map> validate = value -> schemas.keySet().stream()
                .allMatch(key -> {
                    BaseSchema<R> baseSchema = schemas.get(key);
                    Object data = value.get(key);
                    return baseSchema.isValid((R) data);
                });
        addCheck("Validate", validate);
        return this;
    }
}
