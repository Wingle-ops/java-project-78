package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStringSchema {

    private Validator v;
    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void testStringValidator() {
        assertTrue(schema.isValid(""));

        schema.required();
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));

        schema.minLength(7);
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));

        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        var schema1 = v.string().required().minLength(10).minLength(4);
        assertTrue(schema1.isValid("hexlet"));
    }

    @Test
    public void test1() {
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test2() {
        schema.contains("hello");
        assertTrue(schema.isValid("hello, Mark")); // true
        schema.contains("Victor");
        assertFalse(schema.isValid("hello, Mark")); // false
    }

    @Test
    public void test3() {
        schema.minLength(4);
        assertTrue(schema.isValid("hello, Mark")); // true
        schema.minLength(15);
        assertFalse(schema.isValid("hello, Mark")); // false
    }

    @Test
    public void test4() {
        schema.required().minLength(4).contains("Hello");
        assertTrue(schema.isValid("Hello, mister")); // true
        schema.minLength(15).contains("Cat");
        assertFalse(schema.isValid("Hello, mister")); // false
    }

    @Test
    public void test5() {
        schema.minLength(10).minLength(-3).contains("Hi").contains("Dog");
        assertTrue(schema.isValid("Dog")); // true
        schema.required().minLength(10).minLength(-3).contains("Hi").contains("Dog");
        assertFalse(schema.isValid(null)); // false
    }
}
