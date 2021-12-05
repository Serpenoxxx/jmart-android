package BenedictoMatthewJmartFA.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import BenedictoMatthewJmartFA.jmart_android.model.Account;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        getSupportActionBar().hide();

        Account loggedAccount = LoginActivity.loggedAccount();
        TextView nameValue = findViewById(R.id.nameValue);
        nameValue.setText(loggedAccount.name);
        TextView emailValue = findViewById(R.id.emailValue);
        emailValue.setText(loggedAccount.email);
        TextView balanceValue = findViewById(R.id.balanceValue);
        balanceValue.setText(String.valueOf(loggedAccount.balance));
    }
}