package BenedictoMatthewJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import BenedictoMatthewJmartFA.jmart_android.request.CreateProductRequest;
import BenedictoMatthewJmartFA.jmart_android.model.ProductCategory;

public class CreateProductActivity extends AppCompatActivity {

    private ProductCategory category;
    private boolean conditionUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        getSupportActionBar().hide();

        EditText productName = findViewById(R.id.createProductName);
        EditText productWeight = findViewById(R.id.createProductWeight);
        EditText productPrice = findViewById(R.id.createProductPrice);
        EditText productDiscount = findViewById(R.id.createProductDiscount);
        RadioButton radioNew = findViewById(R.id.radioNew);
        RadioButton radioUsed = findViewById(R.id.radioUsed);
        Spinner productCategory = findViewById(R.id.categorySpinner);
        Spinner productShipmentPlans = findViewById(R.id.shipmentPlanSpinner);

        Button productCreate = findViewById(R.id.buttonCreate);
        Button productCancel = findViewById(R.id.buttonCancel);

        productCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        productCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionUsed = !radioNew.isChecked();

                byte validShipmentPlan = checkShipmentPlan(productShipmentPlans);

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            if (jObject != null) {
                                Toast.makeText(CreateProductActivity.this, "Product Successfully Made!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CreateProductActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreateProductActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateProductActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateProductActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                    }
                };
                CreateProductRequest createRequest = new CreateProductRequest(
                        String.valueOf(LoginActivity.loggedAccount().id),
                        productName.getText().toString(),
                        productWeight.getText().toString(),
                        String.valueOf(conditionUsed),
                        productPrice.getText().toString(),
                        productDiscount.getText().toString(),
                        productCategory.getSelectedItem().toString(),
                        String.valueOf(validShipmentPlan),
                        listener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(CreateProductActivity.this);
                queue.add(createRequest);
            }
        });
    }

    private byte checkShipmentPlan(Spinner productShipmentPlan) {
        if (productShipmentPlan.getSelectedItem() == "INSTANT") {
            return (byte)00000001;
        } else if (productShipmentPlan.getSelectedItem() == "SAME DAY") {
            return (byte)00000010;
        } else if (productShipmentPlan.getSelectedItem() == "NEXT DAY") {
            return (byte)00000100;
        } else if (productShipmentPlan.getSelectedItem() == "REGULER") {
            return (byte)00001000;
        } else if (productShipmentPlan.getSelectedItem() == "KARGO") {
            return (byte)00010000;
        }
        return 0;
    }
}