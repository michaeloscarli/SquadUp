package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import com.bailey.mobile.squadup.R;
import android.view.GestureDetector;
import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;

public class IntroductoryActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.home);

        RelativeLayout myView = (RelativeLayout) this.findViewById(R.id.home);

        myView.setOnTouchListener(new swipe(IntroductoryActivity.this) {

            public void onSwipeLeft() {
                Intent intent = new Intent(IntroductoryActivity.this, frag1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

        });
    }
}