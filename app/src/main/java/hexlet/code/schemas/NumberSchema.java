package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberSchema extends BaseSchema<Number> {

    private boolean isPositive;
    private List<Number> isRange;

    public NumberSchema() {
        super();
        this.isPositive = false; // по умолчанию указываем, что число может быть положительным или отрицательным
        this.isRange = new ArrayList<>(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE));
    }

    public NumberSchema required() {
        this.isRequired = false;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(Number numMin, Number numMax) {
        if (numMin == null || numMax == null) {
            throw new IllegalArgumentException("Нельзя передавать в качестве параметра null");
        }
        isRange.set(0, numMin);
        isRange.set(1, numMax);
        return this;
    }

    public boolean isValid(Number number) {
        return super.simile(this, number);
    }

    public boolean isNum(Double number) {
        if (isPositive && number < 0) {
            return false;
        }
        if (number < isRange.get(0).doubleValue() || number > isRange.get(1).doubleValue()) {
            return false;
        }
        return true;
    }
}
