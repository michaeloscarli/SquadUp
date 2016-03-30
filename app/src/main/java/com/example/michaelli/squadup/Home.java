package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by michaelli on 3/29/16.
 */
public class Home extends Activity{
    DictionaryHelper dictionaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO setContentView(R.layout.player);
        dictionaryHelper = new DictionaryHelper(this);

    }
}
