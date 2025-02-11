package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {

    private int sizeMap;
    private Map<String, StringSchema> controlMap;

    public MapSchema() {
        super();
        this.sizeMap = 0;
        controlMap = new HashMap<>();
    }

    public MapSchema required() {
        this.isRequired = false;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeMap = size;
        return this;
    }

    public boolean isValid(Map<String, String> map) {
        return super.simile(this, map);
    }

    public boolean isMap(Map<String, String> map) {
        if (!controlMap.isEmpty()) {
            for (Map.Entry<String, String> el : map.entrySet()) {
                StringSchema schema = controlMap.get(el.getKey());
                if (schema == null || !schema.isValid(el.getValue())) {
                    return false;
                }
            }
        }
        return map.size() >= sizeMap;
    }

    public void shape(Map<String, BaseSchema<String>> map) {
        for (Map.Entry<String, BaseSchema<String>> el : map.entrySet()) {
            controlMap.put(el.getKey(), (StringSchema) el.getValue());
        }
    }
}
