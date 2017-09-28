package com.example.rasik.resumebuilder.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rasik.resumebuilder.Fragments.EducationFragment;
import com.example.rasik.resumebuilder.Fragments.InterestFragment;
import com.example.rasik.resumebuilder.Fragments.PersonalFragment;

/**
 * Created by rasik on 28/9/17.
 */

public class TabPageAdapter extends FragmentPagerAdapter {

    private int pageCount= 3;

    public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new PersonalFragment();
            case 1:
                return new EducationFragment();
            case 2:
                return new InterestFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

    public void setPageCount(int pageCount) {
        this.pageCount=pageCount;
    }
}