package com.example.rasik.resumebuilder.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.rasik.resumebuilder.R;

import java.util.Calendar;


/**
 * Created by rasik on 28/9/17.
 */

public class PersonalFragment extends Fragment {

    EditText input_date;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.personal_info_fragment, container, false);
        input_date = (EditText)rootView.findViewById(R.id.input_date);
        input_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DatePickerFragment dpf = new DatePickerFragment().newInstance();
                dpf.setCallBack(onDate);
                dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
            }
        });


        return rootView;
    }


    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            input_date =  getActivity().findViewById(R.id.input_date);
            input_date.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };


}
