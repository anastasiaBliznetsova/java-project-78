package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class Scem {
    private static Map<String, Boolean> mapCheck = new HashMap<>();
    public static void addCheck(String check, Boolean status) {
        mapCheck.put(check, status);
    }
    
    
}
