package com.example.rasik.resumebuilder;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.rasik.resumebuilder.Adapter.TabPageAdapter;
import com.example.rasik.resumebuilder.Fragments.EducationFragment;
import com.example.rasik.resumebuilder.Fragments.SkillFragment;
import com.example.rasik.resumebuilder.Fragments.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fragmentManager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PersonalFragment(), "Personal Info");
        adapter.addFragment(new EducationFragment(), "Educational Info");
        adapter.addFragment(new SkillFragment(), "Skill Info");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.fixed) {
            item.setChecked(!item.isChecked());

            if (item.isChecked()) {
                adapter.setPageCount(3);
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
            }
            else {
                adapter.setPageCount(10);
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }

            adapter.notifyDataSetChanged();

            if (viewPager.getCurrentItem()>=3) {
                viewPager.setCurrentItem(2);
            }

            return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}