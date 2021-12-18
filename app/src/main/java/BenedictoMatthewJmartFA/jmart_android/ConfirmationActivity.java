package BenedictoMatthewJmartFA.jmart_android;

import static BenedictoMatthewJmartFA.jmart_android.ProductDetailActivity.getPayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import BenedictoMatthewJmartFA.jmart_android.model.Payment;
import BenedictoMatthewJmartFA.jmart_android.request.AcceptRequest;
import BenedictoMatthewJmartFA.jmart_android.request.CancelRequest;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        TextView ConfAddress = findViewById(R.id.ConfAddress);
        TextView ConftProductCount = findViewById(R.id.ConftProductCount);
        System.out.println(getPayment().productCount);
        System.out.println(getPayment().shipment.address);
        String address = getPayment().shipment.address;
        ConfAddress.setText(address);
        ConftProductCount.setText(String.valueOf(getPayment().productCount));

        Button AcceptConfirmation = findViewById(R.id.AcceptConfirmation);
        Button CancelConfirmation = findViewById(R.id.CancelConfirmation);

        AcceptConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(ConfirmationActivity.this, "Accepted", Toast.LENGTH_LONG);
                        Intent intent = new Intent (ConfirmationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(ConfirmationActivity.this, "Error", Toast.LENGTH_LONG);

                    }
                };
                AcceptRequest accReq = new AcceptRequest(getPayment().id,listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(ConfirmationActivity.this);
                requestQueue.add(accReq);
            }
        });

        CancelConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ConfirmationActivity.this, "Cancelled", Toast.LENGTH_LONG);
                        System.out.println(response);
                        finish();
                    }

                };
                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(ConfirmationActivity.this, "Error", Toast.LENGTH_LONG);

                    }
                };
                CancelRequest cancelReq = new CancelRequest(getPayment().id,listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(ConfirmationActivity.this);
                requestQueue.add(cancelReq);
            }
        });
    }

}