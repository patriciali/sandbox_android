package com.formagrid.hellotest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelloActivity extends Activity {

    private TabLayout mTabLayout;

    private ViewPager mPager;
    private HelloPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new HelloPagerAdapter(getFragmentManager());
        mPager.setAdapter(mAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class HelloPagerAdapter extends FragmentPagerAdapter {

        public HelloPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new LeftFragment();
            } else if (position == 1) {
                return new RightFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "LEFT";
            } else if (position == 1) {
                return "RIGHT";
            }
            return "FUCK YOU";
        }

    }

    public static class LeftFragment extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment, container, false);
            View tv = v.findViewById(R.id.text_view);
            ((TextView)tv).setText("left fragment");
            return v;
        }

    }

    public static class RightFragment extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment, container, false);
            View tv = v.findViewById(R.id.text_view);
            ((TextView)tv).setText("right fragment");
            return v;
        }

    }

}
