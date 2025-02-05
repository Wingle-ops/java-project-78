package hexlet.code.schemas;

public class StringSchema {

    private boolean isRequired;
    private int isMinLength;
    private String isContains;

    public StringSchema() {
        this.isRequired = true;
        this.isMinLength = 0;
        this.isContains = "";
    }

    public StringSchema required() {
        this.isRequired = false;
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
        if (!isRequired && string == null) {
            return false;
        }
        if (string != null) {
            if (string.length() < isMinLength) {
                return false;
            }
            if (!string.toLowerCase().contains(isContains.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
