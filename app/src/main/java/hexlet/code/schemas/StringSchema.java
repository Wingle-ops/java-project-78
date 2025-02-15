package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        required = false;
        return this;
    }

    public StringSchema minLength(int num) {
        Predicate<String> length = str -> str.length() >= num;
        addCheck("Length", length);
        return this;
    }

    public StringSchema contains(String string) {
        Predicate<String> contain = str -> str.contains(string);
        addCheck("Contains", contain);
        return this;
    }
}
