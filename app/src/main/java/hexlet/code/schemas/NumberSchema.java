package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        required = false;
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = num -> num > 0;
        addCheck("isPositive", isPositive);
        return this;
    }

    public NumberSchema range(int numMin, int numMax) {
        Predicate<Integer> isRange = num -> num >= numMin && num <= numMax;
        addCheck("isRange", isRange);
        return this;
    }
}
