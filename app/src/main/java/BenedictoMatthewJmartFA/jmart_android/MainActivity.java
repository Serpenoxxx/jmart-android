package BenedictoMatthewJmartFA.jmart_android;

import static BenedictoMatthewJmartFA.jmart_android.LoginActivity.loggedAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PageAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:{
                        //can use set icon
                        tab.setText("Products");
                        break;
                    }
                    case 1:{
                        //color too
                        tab.setText("Filter");
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

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}