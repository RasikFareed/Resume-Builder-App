package com.example.rasik.resumebuilder.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.rasik.resumebuilder.R;

import java.util.Calendar;


/**
 * Created by rasik on 28/9/17.
 */

public class PersonalFragment extends Fragment {

    EditText input_date,inputFirstName,inputLastName,input_email,input_street,input_district,input_pincode;
    Button next1;
    ViewPager viewPager;
    TabLayout tabLayout;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.personal_info_fragment, container, false);

        inputFirstName = (EditText)rootView.findViewById(R.id.inputFirstName);
        inputLastName = (EditText) rootView.findViewById(R.id.inputLastName);
        input_email = (EditText)rootView.findViewById(R.id.input_email);
        input_date = (EditText)rootView.findViewById(R.id.input_date);
        input_street = (EditText)rootView.findViewById(R.id.input_street);
        input_district = (EditText)rootView.findViewById(R.id.input_district);
        input_pincode = (EditText)rootView.findViewById(R.id.input_pincode);

        next1 = (Button)rootView.findViewById(R.id.btn_next);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        input_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DatePickerFragment dpf = new DatePickerFragment().newInstance();
                dpf.setCallBack(onDate);
                dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");

            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             viewPager = getActivity().findViewById(R.id.viewpager);
             viewPager.setCurrentItem(1);
            }
        });

        return rootView;
    }


    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {

            input_date =  getActivity().findViewById(R.id.input_date);
            input_date.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)+ "-" + String.valueOf(dayOfMonth));
        }
    };


}
