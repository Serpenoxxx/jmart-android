package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a product creation request.
 * Inherits StringRequest
 *
 * @author Benedicto Matthew W
 */

public class CreateProductRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8090/product/create";
    private final Map<String,String> params;

    /**
     * Requests to put the params according to the account attributes in the HashMap.
     * Creates a product and show it in the products section.
     *
     * @param  accountId represents the account that created the product.
     * @param  name represents the product's name.
     * @param  weight represents the product's weight.
     * @param  conditionUsed represents the product's condition.
     * @param  price represents the product's price.
     * @param  discount represents the discount applied to the product.
     * @param  category represents the product's category.
     * @param  shipmentPlans represents the shipment plan used to send the product.
     * @param  listener listens to responses.
     * @param  errorListener listens to error responses.
     */

    public CreateProductRequest(String accountId, String name, String weight, String conditionUsed,
                                String price, String discount, String category, String shipmentPlans,
                                Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("accountId",accountId);
        params.put("name",name);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("price",price);
        params.put("discount",discount);
        params.put("category",category);
        params.put("shipmentPlans",shipmentPlans);
    }
    @Override
    public Map<String, String> getParams(){return params;}
}
