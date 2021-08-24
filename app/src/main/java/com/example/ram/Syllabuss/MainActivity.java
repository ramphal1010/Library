package com.example.ram.Syllabuss;

import android.os.Bundle;
import com.example.ram.R;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabuss_layout);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),2);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        @Nonnull

        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new First_yearFragment();
                case 1:
                    return new Second_Year_Fragment();
                case 2:
                    return new Third_Year_Fragment();
                case 3:
                    return new Fourth_Year_Fragment();
                default:
                    return null;
            }


        }
        public int getCount(){
            return 4;
        }
        public CharSequence getPageTitle(int position){
            switch (position)
            {
                case 0:
                    return "Ist Year";
                case 1:
                    return  "2nd Year";
                case 2:
                    return  "3rd Year";
                case 3:
                    return "4th Year";

            }
            return  null;
        }
    }

}