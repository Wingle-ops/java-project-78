package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestMapSchema {

    private Validator v;
    private MapSchema schema;
    private Map<String, String> map;
    private Map<String, BaseSchema<String>> schemas;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
        map = new HashMap<>();
        schemas = new HashMap<>();
    }

    @Test
    public void test1() {
        assertTrue(schema.isValid(new HashMap<>())); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test2() {
        map.put("Hello", "Cat");
        assertTrue(schema.isValid(map)); // true
        schema.sizeof(2);
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void test3() {
        assertTrue(schema.isValid(null)); // true
        map.put("Hello", "Cat");
        schema.sizeof(0).required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test4() {
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", "Chechin");
        assertTrue(schema.isValid(map)); // true
    }

    @Test
    public void test5() {
        schemas.put("firstName", v.string().required().contains("Misha"));
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", "Chechin");
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void test6() {
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", null);
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void testLesson1() {
        schema.isValid(null); // true
        schema.required();
        schema.isValid(null); // false

        schema.isValid(new HashMap<>()); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        schema.isValid(data); // true
        schema.sizeof(2);
        schema.isValid(data);  // false
        data.put("key2", "value2");
        schema.isValid(data); // true
    }

    @Test
    public void testLesson2() {
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        schema.isValid(human1); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        schema.isValid(human2); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        schema.isValid(human3); // false
    }
}
