package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a submission request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class SubmitRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/payment/%d/submit";
    private static Map<String, String> params = new HashMap<>();

    /** Requests a submission of a receipt
     *
     * @param  id represents the id
     * @param  receipt represents the receipt
     * @param  listener listens to responses
     * @param  errorListener listens to error responses
     */

    public SubmitRequest(int id, String receipt, Response.Listener<String> listener,
                         Response.ErrorListener errorListener){
        super(Request.Method.POST, String.format(URL, id), listener, errorListener);
        params.put("receipt",receipt);
    }
    public Map<String, String> getParams() { return params; }
}
