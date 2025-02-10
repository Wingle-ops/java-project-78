package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNumberSchema {

    private Validator v = new Validator();

    @Test
    public void test1() {
        NumberSchema schema = v.number();
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }

    @Test
    public void test2() {
        NumberSchema schema = v.number();
        schema.positive();
        assertFalse(schema.isValid(-2)); // false
    }

    @Test
    public void test3() {
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(10)); // true
        schema.range(-2, 7);
        assertFalse(schema.isValid(10)); // false
    }

    @Test
    public void test4() {
        NumberSchema schema = v.number();
        schema.required().positive().range(-2, 7).range(10, 20);
        assertTrue(schema.isValid(15)); // true
    }

    @Test
    public void test5() {
        NumberSchema schema = v.number();
        schema.positive();
        assertTrue(schema.isValid(null)); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
    }
}
