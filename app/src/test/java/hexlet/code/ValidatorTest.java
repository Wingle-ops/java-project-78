package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {
    
    @Test
    public void StringSchemaTest1() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertTrue(schema.isValid(null)); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void StringSchemaTest2() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.contains("hello");
        assertTrue(schema.isValid("hello, Mark")); // true
        schema.contains("Victor");
        assertFalse(schema.isValid("hello, Mark")); // false
    }

    @Test
    public void StringSchemaTest3() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(4);
        assertTrue(schema.isValid("hello, Mark")); // true
        schema.minLength(15);
        assertFalse(schema.isValid("hello, Mark")); // false
    }

    @Test
    public void StringSchemaTest4() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required().minLength(4).contains("Hello");
        assertTrue(schema.isValid("Hello, mister")); // true
        schema.required().minLength(15).contains("Cat");
        assertFalse(schema.isValid("Hello, mister")); // false
    }

    @Test
    public void StringSchemaTest5() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(10).minLength(-3).contains("Hi").contains("Dog");
        assertTrue(schema.isValid("Dog")); // true
        schema.required().minLength(10).minLength(-3).contains("Hi").contains("Dog");
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void NumberSchemaTest1() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void NumberSchemaTest2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        assertFalse(schema.isValid(-2)); // false
    }

    @Test
    public void NumberSchemaTest3() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(10)); // true
        schema.range(-2, 7);
        assertFalse(schema.isValid(10)); // false
    }

    @Test
    public void NumberSchemaTest4() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required().positive().range(-2, 7).range(10, 20);
        assertTrue(schema.isValid(15)); // true
    }

    @Test
    public void NumberSchemaTest5() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.positive();
        assertTrue(schema.isValid(null)); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void MapTest1() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertTrue(schema.isValid(new HashMap<>())); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void MapTest2() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "Cat");
        assertTrue(schema.isValid(map)); // true
        schema.sizeof(2);
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void MapTest3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        assertTrue(schema.isValid(null)); // true
        map.put("Hello", "Cat");
        schema.sizeof(0).required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void MapTest4() {
        Validator v = new Validator();
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
    public void MapTest5() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("Misha"));
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Mihail");
        map.put("lastName", "Chechin");
        assertFalse(schema.isValid(map)); // false
    }

    @Test
    public void MapTest6() {
        Validator v = new Validator();
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
