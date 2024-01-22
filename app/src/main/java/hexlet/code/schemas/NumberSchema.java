package hexlet.code.schemas;
import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addCheck("instanceof",
                value -> value == null || value instanceof Integer);
    }
    public final NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }
    public final NumberSchema positive() {
        addCheck("positive",
                value -> value == null || (value instanceof Integer && (int) value > 0));
        return this;
    }

    public final NumberSchema range(int minValue, int maxValue) {
        addCheck("range",
                value -> value != null && (int) value <= maxValue && (int) value >= minValue);
        return this;
    }

}
