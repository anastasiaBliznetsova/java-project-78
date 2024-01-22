package hexlet.code.schemas;
import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addCheck("instanceof",
                value -> value == null || value instanceof Map<?, ?>);
    }
    public final MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }
    public final MapSchema sizeof(int value) {
        addCheck("sizeof",
                map -> map != null && ((Map<?, ?>) map).size() == value);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> mapSchemas) {
        addCheck("shape", map -> mapSchemas.entrySet().stream()
                .allMatch(keyAndValue -> keyAndValue.getValue()
                        .isValid(((Map<?, ?>) map).get(keyAndValue.getKey()))));
        return this;
    }
}
