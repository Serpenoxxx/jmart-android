package BenedictoMatthewJmartFA.jmart_android.request;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

/**
 * Creates a request to fetch products.
 *
 * @author Benedicto Matthew W
 */

public class RequestFactory
{
    private static final String URL_FORMAT_ID = "http://10.0.2.2:8090/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:8090/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_PRODUCT = "http://10.0.2.2:8090/product/getFiltered?page=%s&pageSize=%s&accountId=%d&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";
    private static final String URL_PAYMENT = "http://10.0.2.2:8090/payment/%d/getPayment";
    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /** Requests products according to sent params.
     *
     * @param  page represents page
     * @param  pageSize represents number of products shown in a single page
     * @param  id represents the id
     * @param  search represents the name of searched item
     * @param  minPrice represents the minimum price of products
     * @param  maxPrice represents the maximum price of products
     * @param  category represents the category of products
     * @param  conditionUsed represents the condition of products
     * @param listener listens to responses
     * @param errorListener listens to error responses
     */

    public static StringRequest getProduct(
            int page,
            int pageSize,
            int id,
            String search,
            String minPrice,
            String maxPrice,
            String category,
            String conditionUsed,
            Response.Listener<String> listener,
            Response.ErrorListener errorListener
    )
    {
        String url = String.format(URL_FORMAT_PRODUCT,page,pageSize,id,search,minPrice,maxPrice,category,conditionUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);

    }

    public static StringRequest getPayment(
            int id,
            Response.Listener<String> listener,
            Response.ErrorListener errorListener
    ){
        String url = String.format(URL_PAYMENT,id);
        return new StringRequest(Request.Method.GET,url,listener,errorListener);
    }

}