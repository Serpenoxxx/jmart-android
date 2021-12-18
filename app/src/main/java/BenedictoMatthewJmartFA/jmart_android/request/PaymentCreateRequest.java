package BenedictoMatthewJmartFA.jmart_android.request;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

import BenedictoMatthewJmartFA.jmart_android.ProductsFragment;

/**
 * Creates a payment request.
 * Inherits StringRequest.
 *
 * @author Benedicto Matthew W
 */

public class PaymentCreateRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/payment/create";
    private final Map<String,String> params;

    /**
     * Requests to put the params according to the payment attributes in the HashMap.
     * Completes the payment according to the product and account details.
     *
     * @param  productCount represents the number of products which will be purchased.
     * @param shipmentAddress represents the destination address.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public PaymentCreateRequest(String buyerId, String productId, String productCount, String shipmentAddress, String  shipmentPlan,
                                Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId",buyerId);
        params.put("productId",productId);
        params.put("productCount",productCount);
        params.put("shipmentAddress",shipmentAddress);
        params.put("shipmentPlan",shipmentPlan);

    }
    @Override
    public Map<String,String> getParams(){return this.params;}
}
