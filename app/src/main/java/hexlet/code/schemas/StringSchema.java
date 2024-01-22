package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        addCheck("instanceof",
                value -> value == null || value instanceof String);
    }

    public final StringSchema required() {
        addCheck("required", string -> (string != null && !string.equals("")));
        return this;
    }

    public final StringSchema minLength(int value) {
        addCheck("minLength",
                string -> string != null && ((String) string).length() >= value);
        return this;
    }

    public final StringSchema contains(String substring) {
        addCheck("contains",
                string -> substring != null && string != null && string.toString().contains(substring));
        return this;
    }

}
