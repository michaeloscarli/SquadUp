package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import com.bailey.mobile.squadup.R;

/**
 * Created by jackiehuey on 4/6/16.
 */

public class frag2 extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_intro_page_2);

        RelativeLayout myView = (RelativeLayout) this.findViewById(R.id.fragment2);

        myView.setOnTouchListener(new swipe(frag2.this) {

            public void onSwipeLeft() {
                Intent intent = new Intent(frag2.this, frag3.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

            public void onSwipeRight() {
               Intent intent = new Intent(frag2.this, frag1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });
    }
}



