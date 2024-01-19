package hexlet.code.schemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate> checks;
    protected BaseSchema() {
        this.checks = new HashMap<>();
    }
    public final boolean isValid(Object object) {
        return checks.values().stream().allMatch(check -> check.test(object));
    }
    protected void addCheck(String nameCheck, Predicate<Object> value) {
        checks.put(nameCheck, value);
    }

}
