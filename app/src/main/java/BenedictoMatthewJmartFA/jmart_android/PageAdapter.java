package BenedictoMatthewJmartFA.jmart_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ProductsFragment();
        }
        return new FilterFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
}
