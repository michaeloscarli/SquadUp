package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bailey.mobile.squadup.R;

/**
 * Created by michaelli on 3/29/16.
 */
public class Login extends Activity{
    DictionaryHelper dictionaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //create dictionary helper that accesses/creates database
        dictionaryHelper = new DictionaryHelper(this);

    }
}