package BenedictoMatthewJmartFA.jmart_android;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView name = findViewById(R.id.hello);
        name.setText("Hello "+ loggedAccount().name);
    }
}