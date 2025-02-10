//package hexlet.code;
//
//import hexlet.code.schemas.StringSchema;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class TestStringSchema {
//
//    private Validator v = new Validator();
//
//    @Test
//    public void test1() {
//        StringSchema schema = v.string();
//        assertTrue(schema.isValid(null)); // true
//        schema.required();
//        assertFalse(schema.isValid(null)); // false
//    }
//
//    @Test
//    public void test2() {
//        StringSchema schema = v.string();
//        schema.contains("hello");
//        assertTrue(schema.isValid("hello, Mark")); // true
//        schema.contains("Victor");
//        assertFalse(schema.isValid("hello, Mark")); // false
//    }
//
//    @Test
//    public void test3() {
//        StringSchema schema = v.string();
//        schema.minLength(4);
//        assertTrue(schema.isValid("hello, Mark")); // true
//        schema.minLength(15);
//        assertFalse(schema.isValid("hello, Mark")); // false
//
//    }
//
//    @Test
//    public void test4() {
//        StringSchema schema = v.string();
//        schema.required().minLength(4).contains("Hello");
//        assertTrue(schema.isValid("Hello, mister")); // true
//        schema.required().minLength(15).contains("Cat");
//        assertFalse(schema.isValid("Hello, mister")); // false
//    }
//
//    @Test
//    public void test5() {
//        StringSchema schema = v.string();
//        schema.minLength(10).minLength(-3).contains("Hi").contains("Dog");
//        assertTrue(schema.isValid("Dog")); // true
//        schema.required().minLength(10).minLength(-3).contains("Hi").contains("Dog");
//        assertFalse(schema.isValid(null)); // false
//    }
//}
