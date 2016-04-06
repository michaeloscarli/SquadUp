package com.example.michaelli.squadup.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailey.mobile.squadup.R;

public class FindingExploreFragment extends Fragment {
    ImageView imageView;
    TextView introExplore;
    TextView introAroundYou;
    ImageView introChannels;
    Button introFinish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_intro_page_4, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.intro_compass);

        introFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return rootView;
    }
}