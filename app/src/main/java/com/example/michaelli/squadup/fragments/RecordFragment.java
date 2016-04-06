package com.example.michaelli.squadup.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bailey.mobile.squadup.R;

public class RecordFragment extends Fragment {
    ImageView imageView;
    TextView introShareText;
    ImageView introShareIcon;
    TextView anonymousText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_intro_page_1, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.anonymous_icon);
        introShareIcon = (ImageView) rootView.findViewById(R.id.intro_share_icon);

        return rootView;
    }
}