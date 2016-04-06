package com.example.michaelli.squadup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.bailey.mobile.squadup.R;
import com.example.michaelli.squadup.fragments.FindingExploreFragment;
import com.example.michaelli.squadup.fragments.RecordFragment;
import com.example.michaelli.squadup.fragments.TrendingPageFragment;
import com.example.michaelli.squadup.fragments.UpdateFragment;

public class IntroductoryActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        }
    public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new FindingExploreFragment();
                case 1:
                    return new RecordFragment();
                case 2:
                    return new UpdateFragment();
                default:
                    return new TrendingPageFragment();
            }
        }

    }
