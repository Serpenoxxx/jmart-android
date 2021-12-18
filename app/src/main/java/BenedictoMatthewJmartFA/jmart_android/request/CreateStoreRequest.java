package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a store creation request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class CreateStoreRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/account/%d/registerStore";
    private static Map<String, String> params = new HashMap<>();

    /**
     * Requests to put the params according to the store attributes in the HashMap.
     * Creates a store.
     *
     * @param  name represents the store's name.
     * @param  address represents the store's address.
     * @param  phoneNumber represenets the store's phone number.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public CreateStoreRequest(int id, String name, String address, String phoneNumber, Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
        super(Request.Method.POST, String.format(URL, id), listener, errorListener);
        params.put("name", name);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
    }

    public Map<String, String> getParams() { return params; }
}
