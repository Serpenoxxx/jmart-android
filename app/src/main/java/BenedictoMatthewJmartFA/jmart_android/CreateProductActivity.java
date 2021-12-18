package BenedictoMatthewJmartFA.jmart_android;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

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

/** Allows user to create product based on the params set by user.
 *
 * @author Benedicto Matthew W
 */

public class CreateProductActivity extends AppCompatActivity {

    private ProductCategory category;
    private boolean conditionUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

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
                finish();
            }
        });

        /** Sends CreateProductRequest on create button click
         * Shows toast on error
         */

        productCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionUsed = !radioNew.isChecked();

                byte validShipmentPlan = checkShipmentPlan(productShipmentPlans);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
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
                        String.valueOf(loggedAccount().id),
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
        byte bit = 1;
        if (productShipmentPlan.getSelectedItem().toString().equals("INSTANT")) {
            bit = (byte)1;
        } else if (productShipmentPlan.getSelectedItem().toString().equals("SAME DAY")) {
            bit = (byte)2;
        } else if (productShipmentPlan.getSelectedItem().toString().equals("NEXT DAY")) {
            bit = (byte)4;
        } else if (productShipmentPlan.getSelectedItem().toString().equals("REGULER")) {
            bit = (byte)8;
        } else if (productShipmentPlan.getSelectedItem().toString().equals("KARGO")) {
            bit = (byte)16;
        }
        return bit;
    }
}