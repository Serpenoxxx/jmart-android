package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates an account registration request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class RegisterRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/account/register";
    private final Map<String, String> params;

    /**
     * Requests to put the params according to the register attributes in the HashMap.
     * Registers an account with the entered credentials.
     *
     * @param  name represents the account name to be created.
     * @param  email represents the email of the account.
     * @param  password represents the password of the account.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){

        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);

    }
public Map<String, String> getParams(){
    return params;
    }
}
