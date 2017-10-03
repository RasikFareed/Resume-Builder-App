package com.example.rasik.resumebuilder.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rasik.resumebuilder.R;
import com.example.rasik.resumebuilder.Util.TinyDB;

/**
 * Created by rasik on 28/9/17.
 */

public class EducationFragment extends Fragment {

    EditText inputTenthSchoolName,inputTenthYear,inputTenthPercentage,inputTwelthSchoolName,inputTwelthYear,inputTwelthPercentage,inputCollegeName,
            inputCollegeYear,inputCollegePercentage;
    Button btn_next2;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.education_fragment, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        inputTenthSchoolName = (EditText)rootView.findViewById(R.id.inputTenthSchoolName);
        inputTenthYear = (EditText)rootView.findViewById(R.id.inputTenthYear);
        inputTenthPercentage = (EditText)rootView.findViewById(R.id.inputTenthPercentage);

        inputTwelthSchoolName = (EditText)rootView.findViewById(R.id.inputTwelthSchoolName);
        inputTwelthYear = (EditText)rootView.findViewById(R.id.inputTwelthYear);
        inputTwelthPercentage = (EditText)rootView.findViewById(R.id.inputTwelthPercentage);

        inputCollegeName = (EditText)rootView.findViewById(R.id.inputCollegeName);
        inputCollegeYear = (EditText)rootView.findViewById(R.id.inputCollegeYear);
        inputCollegePercentage = (EditText)rootView.findViewById(R.id.inputCollegePercentage);

        btn_next2 = (Button)rootView.findViewById(R.id.btn_next2);
        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putString("tenth",inputTenthSchoolName.getText().toString().trim());
                tinydb.putString("tenthYear",inputTenthYear.getText().toString().trim());
                tinydb.putString("tenthPercent",inputTenthPercentage.getText().toString().trim());

                tinydb.putString("twelth",inputTwelthSchoolName.getText().toString().trim());
                tinydb.putString("twelthYear",inputTwelthYear.getText().toString().trim());
                tinydb.putString("twelthPercent",inputTwelthPercentage.getText().toString().trim());

                tinydb.putString("college",inputCollegeName.getText().toString().trim());
                tinydb.putString("collegeYear",inputCollegeYear.getText().toString().trim());
                tinydb.putString("collegePercent",inputCollegePercentage.getText().toString().trim());

                viewPager = getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(2);
            }
        });

        return rootView;
    }
}