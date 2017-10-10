package com.example.rasik.resumebuilder.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.rasik.resumebuilder.MainActivity;
import com.example.rasik.resumebuilder.R;
import com.example.rasik.resumebuilder.Util.TinyDB;

import java.util.Calendar;


/**
 * Created by rasik on 28/9/17.
 */

public class PersonalFragment extends Fragment {

    private AwesomeValidation validator;
    EditText input_date,inputFirstName,inputLastName,input_email,input_street,input_district,input_pincode;
    Button next1;
    ViewPager viewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.personal_info_fragment, container, false);

      //  final Map<String, String> aMap = new HashMap<String, String>();
        SharedPreferences mPrefs = this.getContext().getSharedPreferences("mpref", Context.MODE_PRIVATE);
       final SharedPreferences.Editor editor = mPrefs.edit();
       // final Set<String> personalStrings = mPrefs.getStringSet("personalStrings", new HashSet<String>());

        validator = new AwesomeValidation(ValidationStyle.BASIC);

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


        validator.addValidation(inputFirstName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(inputLastName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(input_email,"^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$","Please enter valid email");
        validator.addValidation(input_street,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(input_district,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", "Please enter valid name");
        validator.addValidation(input_pincode,"^[1-9][0-9]{5}$", "Please enter valid pincode");

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if(validator.validate()){

                TinyDB tinydb = new TinyDB(getContext());
                tinydb.putString("firstName",inputFirstName.getText().toString().trim());
                tinydb.putString("lastName",inputLastName.getText().toString().trim());
                tinydb.putString("email",input_email.getText().toString().trim());
                tinydb.putString("date",input_date.getText().toString().trim());
                tinydb.putString("street",input_street.getText().toString().trim());
                tinydb.putString("district",input_district.getText().toString().trim());
                tinydb.putString("pincode",input_pincode.getText().toString().trim());


             viewPager = getActivity().findViewById(R.id.viewpager);
             viewPager.setCurrentItem(1);
             }
             else{
                 Toast.makeText(getActivity(),"Invalid Input",Toast.LENGTH_SHORT).show();
             }
            }
        });

        return rootView;
    }


    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            System.out.println(monthOfYear);
            input_date =  getActivity().findViewById(R.id.input_date);
            input_date.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)+ "-" + String.valueOf(dayOfMonth));
        }
    };


}
