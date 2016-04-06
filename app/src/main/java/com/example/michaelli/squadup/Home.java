package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bailey.mobile.squadup.R;

public class Home extends Activity{
    DictionaryHelper dictionaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dictionaryHelper = new DictionaryHelper(this);

    }
}
