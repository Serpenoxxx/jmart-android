package BenedictoMatthewJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import BenedictoMatthewJmartFA.jmart_android.model.Account;
import BenedictoMatthewJmartFA.jmart_android.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;
    public static Account loggedAccount(){
    return loggedAccount;
    }

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String emailsession, passwordsession;

    @Override
    protected void onStart() {
        super.onStart();
        if (emailsession != null && passwordsession != null) {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject object = new JSONObject(response);
                        if(object != null){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            LoginRequest loginRequest = new LoginRequest(emailsession.toString(), passwordsession.toString(),listener,null);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(loginRequest);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.forget);

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // in shared prefs inside het string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
        emailsession = sharedpreferences.getString(EMAIL_KEY, null);
        passwordsession = sharedpreferences.getString(PASSWORD_KEY, null);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                // below two lines will put values for
                                // email and password in shared preferences.
                                editor.putString(EMAIL_KEY, email.getText().toString());
                                editor.putString(PASSWORD_KEY, password.getText().toString());

                                // to save our data with key and value.
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                loggedAccount = gson.fromJson(object.toString(), Account.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                };
                LoginRequest loginRequest = new LoginRequest(email.getText().toString(), password.getText().toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });


    }

}