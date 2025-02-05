package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Main {
    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required().minLength(2).minLength(5).contains("hi");
        System.out.println(schema.isValid("Hi, mister Mihail"));
        System.out.println(schema.isValid("Hello, mister Mihail"));
    }
}
