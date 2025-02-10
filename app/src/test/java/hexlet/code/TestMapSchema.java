package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapSchema {

    private Validator v = new Validator();

    @Test
    public void test1() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        assertTrue(schema.isValid(new HashMap<>())); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test2() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "Cat");
        assertTrue(schema.isValid(map)); // true
        schema.sizeof(2);
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void test3() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        assertTrue(schema.isValid(null)); // true
        map.put("Hello", "Cat");
        schema.sizeof(0).required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test4() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", "Chechin");
        assertTrue(schema.isValid(map)); // true
    }

    @Test
    public void test5() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("Misha"));
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", "Chechin");
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void test6() {
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        map.put("firstName", "Mihail");
        map.put("lastName", null);
        assertFalse(schema.isValid(map)); // false
    }
}
