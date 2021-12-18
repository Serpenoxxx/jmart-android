package BenedictoMatthewJmartFA.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import BenedictoMatthewJmartFA.jmart_android.model.Account;

/**Shows main menu
 * Inherits AppCombatActivity
 *
 */

public class MainActivity extends AppCompatActivity {
    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String emailsession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Account loggedAccount = LoginActivity.loggedAccount();
        setTitle("Hello "+ loggedAccount.name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        // getting data from shared prefs and
        // storing it in our string variable.
        emailsession = sharedpreferences.getString(EMAIL_KEY, null);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PageAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:{
                        tab.setText("Products");
                        tab.setIcon(R.drawable.ic_baseline_library_books_24);
                        break;
                    }
                    case 1:{
                        tab.setText("Filter");
                        tab.setIcon(R.drawable.ic_baseline_filter_list_24);
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        if (LoginActivity.loggedAccount().store == null) {
            menu.findItem(R.id.add).setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.user:
                Intent intent = new Intent(this, AboutMeActivity.class);
                this.startActivity(intent);
                break;
//          case R.id.search:
//              break;
            case R.id.add:
                Intent addIntent = new Intent(this, CreateProductActivity.class);
                this.startActivity(addIntent);
                break;
            case R.id.logOut:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                // below line will clear
                // the data in shared prefs.
                editor.clear();
                // below line will apply empty
                // data to shared prefs.
                editor.apply();
                // starting mainactivity after
                // clearing values in shared preferences.
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}