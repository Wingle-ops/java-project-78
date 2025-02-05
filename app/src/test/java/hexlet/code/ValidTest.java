package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

public class ValidTest {

    private final Validator v = new Validator();
    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        schema = v.string();
    }

    @Test
    public void test1() {
        schema.contains("Hi");
        assertTrue(schema.isValid("Hi, mister Mihail"));
        schema.contains("Hello");
        assertTrue(schema.isValid("Hi, mister Mihail"));
    }

    @Test
    public void test2() {
        schema.minLength(5);
        assertTrue(schema.isValid("Hi, mister Mihail"));
        assertTrue(schema.isValid("Hi"));
    }

    @Test
    public void test3() {
        schema.required().minLength(10).minLength(2).contains("Hi");
        assertTrue(schema.isValid("Hi, mister"));
        assertTrue(schema.isValid("Hello, mister"));
    }
}
