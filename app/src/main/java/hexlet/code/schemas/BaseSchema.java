package hexlet.code.schemas;

import java.util.Map;

public class BaseSchema<T> {

    protected boolean isRequired;

    BaseSchema() {
        this.isRequired = true; // По умолчанию разрешается null
    }

    public boolean simile(Object object, Object data) {
        if (!isRequired && (data == null || data.equals(""))) {
            return false;
        }
        if (data != null) {
            if (object instanceof StringSchema) {
                return ((StringSchema) object).isStr(data.toString());
            } else if (object instanceof NumberSchema) {
                Double dataD = ((Number) data).doubleValue();
                return ((NumberSchema) object).isNum(dataD);
            } else if (object instanceof MapSchema) {
                return ((MapSchema) object).isMap((Map<String, String>) data);
            }
        }
        return true;
    }
}
