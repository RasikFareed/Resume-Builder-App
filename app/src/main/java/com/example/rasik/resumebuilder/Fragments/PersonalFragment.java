package com.example.rasik.resumebuilder.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rasik.resumebuilder.R;

/**
 * Created by rasik on 28/9/17.
 */

public class PersonalFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.personal_info_fragment, container, false);

        return rootView;
    }
}
