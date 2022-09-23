package function;

import java.util.HashMap;
import java.util.Map;

public class FunctionBase {
    FunctionBase() {
    }

    static final String CONTENT_TYPE = "Content-Type";

    public static Map<String, String> getRequestHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(CONTENT_TYPE,"application/json");
        return headers;
    }
}
