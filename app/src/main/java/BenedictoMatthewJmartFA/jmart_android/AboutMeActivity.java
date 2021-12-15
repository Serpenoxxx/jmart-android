package BenedictoMatthewJmartFA.jmart_android;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
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

import org.json.JSONException;
import org.json.JSONObject;

import BenedictoMatthewJmartFA.jmart_android.model.Account;
import BenedictoMatthewJmartFA.jmart_android.model.Store;
import BenedictoMatthewJmartFA.jmart_android.request.CreateStoreRequest;
import BenedictoMatthewJmartFA.jmart_android.request.RegisterRequest;
import BenedictoMatthewJmartFA.jmart_android.request.TopUpRequest;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        getSupportActionBar().hide();

        Account loggedAccount = LoginActivity.loggedAccount();

        TextView nameValue = findViewById(R.id.NameView);
        nameValue.setText(loggedAccount.name);
        TextView emailValue = findViewById(R.id.EmailView);
        emailValue.setText(loggedAccount.email);
        TextView balanceValue = findViewById(R.id.BalanceView);
        balanceValue.setText(String.valueOf(loggedAccount.balance));

        CardView storeCard = findViewById(R.id.cardViewStore);
        CardView registerCard = findViewById(R.id.cardViewRegister);

        Button registerStoreButton = findViewById(R.id.RegisterStoreButton);
        Button cancelButton = findViewById(R.id.CancelButton);
        Button topUpButton = findViewById(R.id.TopUpButton);

        if ((LoginActivity.loggedAccount().store == null)) {
            registerStoreButton.setVisibility(View.VISIBLE);
            storeCard.setVisibility(View.GONE);
        } else {
            storeCard.setVisibility(View.VISIBLE);
            TextView storeName = findViewById(R.id.storeNameView);
            storeName.setText(LoginActivity.loggedAccount().store.name);
            TextView storeAddress = findViewById(R.id.storeAddressView);
            storeAddress.setText(LoginActivity.loggedAccount().store.address);
            TextView storePhoneNumber = findViewById(R.id.storePhoneNumberView);
            storePhoneNumber.setText(LoginActivity.loggedAccount().store.phoneNumber);
        }

        topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText storeBalance = findViewById(R.id.TopUp);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AboutMeActivity.this, "Top Up Success", Toast.LENGTH_SHORT).show();
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(AboutMeActivity.this, "Listener Error", Toast.LENGTH_LONG).show();
                    }
                };
                TopUpRequest newTopUpRequest = new TopUpRequest(LoginActivity.loggedAccount().id, storeBalance.getText().toString(), listener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(AboutMeActivity.this);
                queue.add(newTopUpRequest);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registerStoreButton.setVisibility(View.VISIBLE);
                registerCard.setVisibility(View.GONE);
            }
        });
        registerStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStoreButton.setVisibility(View.GONE);
                registerCard.setVisibility(View.VISIBLE);

                EditText storeName = findViewById(R.id.RegisterName);
                EditText storeAddress = findViewById(R.id.RegisterAddress);
                EditText storePhoneNumber = findViewById(R.id.RegisterPhone);
                Button registerStore = findViewById(R.id.RegisterButton);
                registerStore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((!storeName.getText().toString().isEmpty()) || (!storeAddress.getText().toString().isEmpty()) ||
                                (!storePhoneNumber.getText().toString().isEmpty())) {
                            Response.Listener<String> listener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jObject = new JSONObject(response);

                                        if (jObject != null) {
                                            Toast.makeText(AboutMeActivity.this, "Success!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(AboutMeActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(AboutMeActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };
                            Response.ErrorListener errorListener = new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(AboutMeActivity.this, "Failed!", Toast.LENGTH_LONG);
                                }
                            };
                            CreateStoreRequest newStoreRequest = new CreateStoreRequest(LoginActivity.loggedAccount().id, storeName.getText().toString(), storeAddress.getText().toString(),
                                    storePhoneNumber.getText().toString(), listener, errorListener);
                            RequestQueue queue = Volley.newRequestQueue(AboutMeActivity.this);
                            queue.add(newStoreRequest);

                        } else {
                            Toast.makeText(AboutMeActivity.this, "Please Fill in The Blanks!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        }
}
