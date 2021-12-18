package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a top up request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class TopUpRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/account/%d/topUp";
    private static Map<String, String> params = new HashMap<>();

    /**
     * Requests to top up the logged account's balance.
     *
     * @param  id represents the account id of currently logged in account.
     * @param  balance represents the amount to be added to the balance.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public TopUpRequest(int id,
                        String balance,
                        Response.Listener<String> listener,
                        Response.ErrorListener errorListener) {
        super(Request.Method.POST, String.format(URL, id), listener, errorListener);
        params.put("balance", String.valueOf(balance));
    }

    public Map<String, String> getParams(){
        return params;
    }

}
