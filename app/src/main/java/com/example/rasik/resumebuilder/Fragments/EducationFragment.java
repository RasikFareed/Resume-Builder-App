package com.example.rasik.resumebuilder.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rasik.resumebuilder.R;

/**
 * Created by rasik on 28/9/17.
 */

public class EducationFragment extends Fragment {

    Button btn_next2;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.education_fragment, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        btn_next2 = (Button)rootView.findViewById(R.id.btn_next2);
        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager = getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });

        return rootView;
    }
}