package hexlet.code.schemas;
import java.util.Objects;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addCheck("instanceof",
                value -> {
                    if (value != null) {
                        return value instanceof Integer;
                    }
                    return true;
                }
        );
    }
    public final NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }
    public final NumberSchema positive() {
        addCheck("positive",
                value -> {
                if (value instanceof Integer) {
                    return (int) value > 0;
                }
                return true;
                });
        return this;
    }

    public final NumberSchema range(int minValue, int maxValue) {
        addCheck("range",
                value -> {
                    if (value instanceof Integer) {
                        return (int) value <= maxValue && (int) value >= minValue;
                    }
                    return true;
                });
        return this;
    }

}
