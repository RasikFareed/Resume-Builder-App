package com.example.rasik.resumebuilder.Fragments;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.rasik.resumebuilder.R;
import com.example.rasik.resumebuilder.Util.TinyDB;
import com.google.common.collect.Range;

/**
 * Created by rasik on 28/9/17.
 */

public class EducationFragment extends Fragment {

    private AwesomeValidation validator;
    EditText inputTenthSchoolName,inputTenthYear,inputTenthPercentage,inputTwelthSchoolName,inputTwelthYear,inputTwelthPercentage,inputCollegeName,
            inputCollegeYear,inputCollegePercentage;
    Button btn_next2;
    ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.education_fragment, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        validator = new AwesomeValidation(ValidationStyle.BASIC);

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


        validator.addValidation(inputTenthSchoolName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(inputTenthYear, Range.closed(1775, Calendar.getInstance().get(Calendar.YEAR)),"Please enter valid year");
        validator.addValidation(inputTenthPercentage, Range.closed(1.0f, 100.0f), "Please enter valid percentage");


        validator.addValidation(inputTwelthSchoolName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(inputTwelthYear, Range.closed(1775, Calendar.getInstance().get(Calendar.YEAR)),"Please enter valid year");
        validator.addValidation(inputTwelthPercentage, Range.closed(1.0f, 100.0f), "Please enter valid percentage");


        validator.addValidation(inputCollegeName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(inputCollegeYear, Range.closed(1775, Calendar.getInstance().get(Calendar.YEAR)),"Please enter valid year");
        validator.addValidation(inputCollegePercentage, Range.closed(0.0f, 10.9f), "Please enter valid percentage");



        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validator.validate()) {
                    TinyDB tinydb = new TinyDB(getContext());
                    tinydb.putString("tenth", inputTenthSchoolName.getText().toString().trim());
                    tinydb.putString("tenthYear", inputTenthYear.getText().toString().trim());
                    tinydb.putString("tenthPercent", inputTenthPercentage.getText().toString().trim());

                    tinydb.putString("twelth", inputTwelthSchoolName.getText().toString().trim());
                    tinydb.putString("twelthYear", inputTwelthYear.getText().toString().trim());
                    tinydb.putString("twelthPercent", inputTwelthPercentage.getText().toString().trim());

                    tinydb.putString("college", inputCollegeName.getText().toString().trim());
                    tinydb.putString("collegeYear", inputCollegeYear.getText().toString().trim());
                    tinydb.putString("collegePercent", inputCollegePercentage.getText().toString().trim());

                    viewPager = getActivity().findViewById(R.id.viewpager);
                    viewPager.setCurrentItem(2);
                }
                else {
                    Toast.makeText(getActivity(),"Invalid Input",Toast.LENGTH_SHORT).show();

                }
            }
        });

        return rootView;
    }
}