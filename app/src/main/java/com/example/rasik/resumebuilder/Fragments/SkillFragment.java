package com.example.rasik.resumebuilder.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.rasik.resumebuilder.R;
import com.example.rasik.resumebuilder.ResumeActivity;
import com.example.rasik.resumebuilder.Util.TinyDB;

/**
 * Created by rasik on 28/9/17.
 */

public class SkillFragment extends Fragment {

    EditText skill1,skill2,skill3,skill4;
    RatingBar ratingBar1,ratingBar2,ratingBar3,ratingBar4;
    Button btn_next3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.skill_fragment, container, false);

        skill1 = (EditText)rootView.findViewById(R.id.skill1);
        skill2 =  (EditText)rootView.findViewById(R.id.skill2);
        skill3 = (EditText)rootView.findViewById(R.id.skill3);
        skill4 = (EditText)rootView.findViewById(R.id.skill4);

        ratingBar1 = (RatingBar)rootView.findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar)rootView.findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar)rootView.findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar)rootView.findViewById(R.id.ratingBar4);


        btn_next3 = (Button)rootView.findViewById(R.id.btn_next3);

        btn_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TinyDB tinyDB = new TinyDB(getContext());
                tinyDB.putString("skill1",skill1.getText().toString().trim());
                tinyDB.putFloat("rating1",ratingBar1.getRating());
                tinyDB.putString("skill2",skill2.getText().toString().trim());
                tinyDB.putFloat("rating2",ratingBar2.getRating());
                tinyDB.putString("skill3",skill3.getText().toString().trim());
                tinyDB.putFloat("rating3",ratingBar3.getRating());
                tinyDB.putString("skill4",skill4.getText().toString().trim());
                tinyDB.putFloat("rating4",ratingBar4.getRating());
                Intent intent = new Intent(getActivity(),ResumeActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}