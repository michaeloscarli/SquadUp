package com.example.michaelli.squadup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import com.bailey.mobile.squadup.R;
import android.database.Cursor;

public class Login extends Activity{
    DictionaryHelper dictionaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dictionaryHelper = new DictionaryHelper(this);
        SQLiteDatabase test = dictionaryHelper.getWritableDatabase();
        Log.d("Login", dictionaryHelper.getDatabaseName());
        Cursor c = test.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        c.moveToFirst();
        c.moveToNext();
        c.moveToNext();
        Log.d("Login", c.getString(0));

        Button login = (Button) findViewById(R.id.intro_login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameHistory.class);
                startActivity(i);
            }
        });

    }
}
