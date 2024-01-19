package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck("instanceof",
                value -> {
                    if (value != null) {
                        return value instanceof String;
                    }
                    return true;
                }
        );
    }

    public StringSchema required() {
        addCheck("required", string -> (string != null && !string.equals("")));
        return this;
    }

    public StringSchema minLength(int value) {
        addCheck("minLength",
                string -> {
                    if (string instanceof String) {
                        return ((String) string).length() >= value;
                    }
                    return true;
                });
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains",
                string -> {
                    if (string instanceof String) {
                        return string.toString().contains(substring);
                    }
                    return true;
                });
        return this;
    }

}
