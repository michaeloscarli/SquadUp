package com.example.michaelli.squadup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bailey.mobile.squadup.R;

/**
 * Created by Jason on 4/6/2016.
 */
public class SummaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.summary, container, false);
    }
}


