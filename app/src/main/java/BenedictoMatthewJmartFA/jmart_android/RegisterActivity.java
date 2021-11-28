package BenedictoMatthewJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import BenedictoMatthewJmartFA.jmart_android.request.RegisterRequest;


public class RegisterActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            EditText email = findViewById(R.id.emailreg);
            EditText password = findViewById(R.id.passwordreg);
            EditText name = findViewById(R.id.namereg);
            Button button = findViewById(R.id.register);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject object = new JSONObject(response);
                                if(object != null){
                                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                  loggedAccount = gson.fromJson(object.toString(), Account.class);
                                    startActivity(intent);
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                                Toast.makeText(RegisterActivity.this, "Register Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Toast.makeText(RegisterActivity.this, "Register Error", Toast.LENGTH_LONG).show();

                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(name.getText().toString(),email.getText().toString(), password.getText().toString(),listener,null);
                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(registerRequest);
                }
            });
        }
}