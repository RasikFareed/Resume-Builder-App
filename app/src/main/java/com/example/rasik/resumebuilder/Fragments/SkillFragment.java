package com.example.rasik.resumebuilder.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.rasik.resumebuilder.R;
import com.example.rasik.resumebuilder.ResumeActivity;

/**
 * Created by rasik on 28/9/17.
 */

public class SkillFragment extends Fragment {

    EditText skill1,skill2,skill3,skill4;
    RatingBar ratingBar1,rattingBar2,ratingBar3,ratingBar4;
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
        rattingBar2 = (RatingBar)rootView.findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar)rootView.findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar)rootView.findViewById(R.id.ratingBar4);

        btn_next3 = (Button)rootView.findViewById(R.id.btn_next3);

        btn_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ResumeActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}