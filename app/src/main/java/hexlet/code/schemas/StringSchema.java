package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class StringSchema {
    private Boolean isRequired = false;
    private Map<String, Boolean> mapCheck = new HashMap<>();
    private Object stringToChecked;
    private boolean statusCheck = true;
    private String substring = "";
    private int minValue;
    private boolean haveMinLength = false;
    public boolean isValid(Object string) {
        this.stringToChecked = string;
        if (string == null || string.equals("")) {
            return !isRequired;
        }
        if (string instanceof String) {
            if (!substring.isEmpty()) {
                contains();
            } else if (haveMinLength) {
                minLength();
            }
            return !mapCheck.containsValue(false);
        }
        return false;
    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int value) {
        if (value >= 0) {
            this.minValue = value;
            haveMinLength = true;
            return this;
        }
        System.out.println("You entered an incorrect value");
        return this;
    }
    public void minLength() {
        if (stringToChecked.toString().length() > minValue) {
            statusCheck = false;
        }
        mapCheck.put("minLength", statusCheck);
    }

    public StringSchema contains(String newSubstring) {
        this.substring = newSubstring;
        return this;
    }

    public void contains() {
        if (!(stringToChecked.toString().contains(substring))) {
            statusCheck = false;
        }
        mapCheck.put("contains", statusCheck);
    }

}
