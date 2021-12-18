package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a login request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class LoginRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/account/login";
    private final Map<String, String> params;

    /**
     * Requests to put the params according to the login attributes in the HashMap.
     * Logs the user in using the entered credentials.
     *
     * @param  email represents the email used to login.
     * @param  password represents the password used to login.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public LoginRequest(String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    public Map<String, String> getParams(){
    return params;
    }
}
