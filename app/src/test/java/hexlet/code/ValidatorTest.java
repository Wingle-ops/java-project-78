//package hexlet.code;
//
//import hexlet.code.schemas.BaseSchema;
//import hexlet.code.schemas.MapSchema;
//import hexlet.code.schemas.NumberSchema;
//import hexlet.code.schemas.StringSchema;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class ValidatorTest {
//
//    private Validator v;
//    private StringSchema schemaStr;
//    private NumberSchema schemaNum;
//    private MapSchema schemaMap;
//    private Map<String, String> map;
//    private Map<String, BaseSchema<String>> schemas;
//
//    @BeforeEach
//    public void beforeEach() {
//        v = new Validator();
//        schemaStr = v.string();
//        schemaNum = v.number();
//        schemaMap = v.map();
//        map = new HashMap<>();
//        schemas = new HashMap<>();
//    }
//
//    @Test
//    public void stringSchemaTest1() {
//        assertTrue(schemaStr.isValid(null)); // true
//        schemaStr.required();
//        assertFalse(schemaStr.isValid(null)); // false
//    }
//
//    @Test
//    public void stringSchemaTest2() {
//        schemaStr.contains("hello");
//        assertTrue(schemaStr.isValid("hello, Mark")); // true
//        schemaStr.contains("Victor");
//        assertFalse(schemaStr.isValid("hello, Mark")); // false
//    }
//
//    @Test
//    public void stringSchemaTest3() {
//        schemaStr.minLength(4);
//        assertTrue(schemaStr.isValid("hello, Mark")); // true
//        schemaStr.minLength(15);
//        assertFalse(schemaStr.isValid("hello, Mark")); // false
//    }
//
//    @Test
//    public void stringSchemaTest4() {
//        schemaStr.required().minLength(4).contains("Hello");
//        assertTrue(schemaStr.isValid("Hello, mister")); // true
//        schemaStr.required().minLength(15).contains("Cat");
//        assertFalse(schemaStr.isValid("Hello, mister")); // false
//    }
//
//    @Test
//    public void stringSchemaTest5() {
//        schemaStr.minLength(10).minLength(-3).contains("Hi").contains("Dog");
//        assertTrue(schemaStr.isValid("Dog")); // true
//        schemaStr.required().minLength(10).minLength(-3).contains("Hi").contains("Dog");
//        assertFalse(schemaStr.isValid(null)); // false
//    }
//
//    @Test
//    public void numberSchemaTest1() {
//        schemaNum.required();
//        assertFalse(schemaNum.isValid(null)); // false
//    }
//
//    @Test
//    public void numberSchemaTest2() {
//        schemaNum.positive();
//        assertFalse(schemaNum.isValid(-2)); // false
//    }
//
//    @Test
//    public void numberSchemaTest3() {
//        assertTrue(schemaNum.isValid(10)); // true
//        schemaNum.range(-2, 7);
//        assertFalse(schemaNum.isValid(10)); // false
//    }
//
//    @Test
//    public void numberSchemaTest4() {
//        schemaNum.required().positive().range(-2, 7).range(10, 20);
//        assertTrue(schemaNum.isValid(15)); // true
//    }
//
//    @Test
//    public void numberSchemaTest5() {
//        schemaNum.positive();
//        assertTrue(schemaNum.isValid(null)); // true
//        schemaNum.required();
//        assertFalse(schemaNum.isValid(null)); // false
//    }
//
//    @Test
//    public void mapTest1() {
//        assertTrue(schemaMap.isValid(new HashMap<>())); // true
//        schemaMap.required();
//        assertFalse(schemaMap.isValid(null)); // false
//    }
//
//    @Test
//    public void mapTest2() {
//        map.put("Hello", "Cat");
//        assertTrue(schemaMap.isValid(map)); // true
//        schemaMap.sizeof(2);
//        assertFalse(schemaMap.isValid(map)); // false
//    }
//
//    @Test
//    public void mapTest3() {
//        assertTrue(schemaMap.isValid(null)); // true
//        map.put("Hello", "Cat");
//        schemaMap.sizeof(0).required();
//        assertFalse(schemaMap.isValid(null)); // false
//    }
//
//    @Test
//    public void mapTest4() {
//        schemas.put("firstName", v.string().required());
//        schemas.put("lastName", v.string().required().minLength(2));
//        schemaMap.shape(schemas);
//        map.put("firstName", "Mihail");
//        map.put("lastName", "Chechin");
//        assertTrue(schemaMap.isValid(map)); // true
//    }
//
//    @Test
//    public void mapTest5() {
//        schemas.put("firstName", v.string().required().contains("Misha"));
//        schemas.put("lastName", v.string().required().minLength(2));
//        schemaMap.shape(schemas);
//        map.put("firstName", "Mihail");
//        map.put("lastName", "Chechin");
//        assertFalse(schemaMap.isValid(map)); // false
//    }
//
//    @Test
//    public void mapTest6() {
//        schemas.put("firstName", v.string().required());
//        schemas.put("lastName", v.string().required().minLength(2));
//        schemaMap.shape(schemas);
//        map.put("firstName", "Mihail");
//        map.put("lastName", null);
//        assertFalse(schemaMap.isValid(map)); // false
//    }
//}
