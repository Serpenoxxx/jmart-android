package BenedictoMatthewJmartFA.jmart_android;


import static BenedictoMatthewJmartFA.jmart_android.InvoiceActivity.invoice;
import static BenedictoMatthewJmartFA.jmart_android.InvoiceActivity.invoiceId;
import static BenedictoMatthewJmartFA.jmart_android.ProductDetailActivity.getPayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import BenedictoMatthewJmartFA.jmart_android.model.Payment;
import BenedictoMatthewJmartFA.jmart_android.request.SubmitRequest;

public class InvoiceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> temp = new ArrayList<>();

        setContentView(R.layout.activity_invoice_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position",0);
        System.out.println(position);
        Gson gson = new Gson();
        for(Payment invo: invoice){
            if(invo.id == invoiceId.get(position)){
                temp.add(invo.shipment.address);
                temp.add(String.valueOf(invo.date));
                temp.add(String.valueOf(invo.rating));
            }
        }
        TextView address = findViewById(R.id.AddressView);
        TextView date = findViewById(R.id.Date2);
        TextView rating = findViewById(R.id.rating2);

        address.setText(temp.get(0));
        date.setText(temp.get(1));
        rating.setText(temp.get(2));

        EditText receipt = findViewById(R.id.Receipt);
        Button SubmitButton = findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        finish();

                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(InvoiceDetail.this, "Error!", Toast.LENGTH_LONG);
                    }
                };
                SubmitRequest submitReq = new SubmitRequest(getPayment().id,receipt.getText().toString(),listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(InvoiceDetail.this);
                requestQueue.add(submitReq);
            }
        });
    }
}
