package com.example.michaelli.squadup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.app.Activity;
import android.view.View.OnTouchListener;
import com.bailey.mobile.squadup.R;
import android.widget.Button;

/**
 * Created by jackiehuey on 4/6/16.
 */
public class frag4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_intro_page_4);


        RelativeLayout myView = (RelativeLayout) this.findViewById(R.id.fragment4);
        Button myButton = (Button) this.findViewById(R.id.intro_finish_button);

        myView.setOnTouchListener(new swipe(frag4.this) {

            public void onSwipeRight() {
                Intent intent = new Intent(frag4.this, frag3.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(frag4.this, Login.class);
                startActivity(i);
            }
        });
    }

}