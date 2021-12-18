package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a acceptation request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class AcceptRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/payment/%d/accept";
    private static Map<String, String> params = new HashMap<>();

    public AcceptRequest(int id, Response.Listener<String> listener,
                         Response.ErrorListener errorListener){
        super(Request.Method.POST, String.format(URL, id), listener, errorListener);
    }

    public Map<String, String> getParams() { return params; }

}
