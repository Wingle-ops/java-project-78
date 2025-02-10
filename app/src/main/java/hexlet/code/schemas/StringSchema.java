package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private int isMinLength;
    private String isContains;

    public StringSchema() {
        super();
        this.isMinLength = 0; // по умолчанию минимальная длина 0
        this.isContains = ""; // по умолчанию нет триггера на содержимое строки
    }

    public StringSchema required() {
        isRequired = false;
        return this;
    }

    public StringSchema minLength(int num) {
        this.isMinLength = num;
        return this;
    }

    public StringSchema contains(String str) {
        this.isContains = str;
        return this;
    }

    public boolean isValid(String string) {
        return super.simile(this, string);
    }

    public boolean isStr(String string) {
        if (string.length() < isMinLength) {
            return false;
        } else if (!string.toLowerCase().contains(isContains.toLowerCase())) {
            return false;
        }
        return true;
    }
}
