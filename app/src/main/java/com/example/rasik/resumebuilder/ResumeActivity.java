package com.example.rasik.resumebuilder;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rasik.resumebuilder.Util.TinyDB;

import static com.example.rasik.resumebuilder.R.id.textView;


/**
 * Created by rasik on 3/10/17.
 */

public class ResumeActivity extends AppCompatActivity {

    TextView resumeName,resumeEmail,careerTextview,schoolTextview1,schoolTextview2,collegeTextview,skillTextview1,skillTextview2,skillTextview3,skillTextview4;
    RatingBar resumeRatingBar1,resumeRatingBar2,resumeRatingBar3,resumeRatingBar4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);
        resumeName = (TextView) findViewById(R.id.resumeName);
        resumeEmail = (TextView) findViewById(R.id.resumeEmail);
        schoolTextview1 = (TextView) findViewById(R.id.schoolTextview1);
        schoolTextview2 = (TextView) findViewById(R.id.schoolTextview2);
        collegeTextview = (TextView) findViewById(R.id.collegeTextview);

        skillTextview1 = (TextView) findViewById(R.id.skillTextview1);
        skillTextview2 = (TextView) findViewById(R.id.skillTextview2);
        skillTextview3 = (TextView) findViewById(R.id.skillTextview3);
        skillTextview4 = (TextView) findViewById(R.id.skillTextview4);

        resumeRatingBar1 = (RatingBar) findViewById(R.id.resumeRatingBar1);
        resumeRatingBar2 = (RatingBar) findViewById(R.id.resumeRatingBar2);
        resumeRatingBar3 = (RatingBar) findViewById(R.id.resumeRatingBar3);
        resumeRatingBar4 = (RatingBar) findViewById(R.id.resumeRatingBar4);


        TinyDB tinyDB = new TinyDB(getApplicationContext());
        resumeName.setText(tinyDB.getString("firstName")+" "+tinyDB.getString("lastName"));
        resumeName.setTextSize(35);
        resumeEmail.setText("Email: "+tinyDB.getString("email"));

        schoolTextview1.setText(tinyDB.getString("tenth")+"- "+tinyDB.getString("tenthYear")+"\n"+"\n"+"Percentage: "+tinyDB.getString("tenthPercent"));
        schoolTextview1.setTextSize(15);
        schoolTextview1.setTypeface(schoolTextview1.getTypeface(), Typeface.BOLD);
        schoolTextview2.setText(tinyDB.getString("twelth")+"- "+tinyDB.getString("twelthYear")+"\n"+"\n"+"Percentage: "+tinyDB.getString("twelthPercent"));
        schoolTextview2.setTextSize(15);
        schoolTextview2.setTypeface(schoolTextview2.getTypeface(), Typeface.BOLD);
        collegeTextview.setText(tinyDB.getString("college")+"- "+tinyDB.getString("collegeYear")+"\n"+"\n"+"CGPA: "+tinyDB.getString("collegePercent"));
        collegeTextview.setTextSize(15);
        collegeTextview.setTypeface(collegeTextview.getTypeface(), Typeface.BOLD);

        skillTextview1.setText(tinyDB.getString("skill1"));
        skillTextview2.setText(tinyDB.getString("skill2"));
        skillTextview3.setText(tinyDB.getString("skill3"));
        skillTextview4.setText(tinyDB.getString("skill4"));

        resumeRatingBar1.setRating(tinyDB.getFloat("rating1"));
        resumeRatingBar2.setRating(tinyDB.getFloat("rating2"));
        resumeRatingBar3.setRating(tinyDB.getFloat("rating3"));
        resumeRatingBar4.setRating(tinyDB.getFloat("rating4"));

    }
}
