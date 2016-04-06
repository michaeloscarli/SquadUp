package com.example.michaelli.squadup;

import android.app.Activity;
import android.content.Intent;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.bailey.mobile.squadup.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



/**
 * Created by michaelli on 4/4/16.
 */
public class GameHistory extends Activity{
    DictionaryHelper dictionaryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_history);
        dictionaryHelper = new DictionaryHelper(this);
        SQLiteDatabase db = dictionaryHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM GAMES;",null);
        populateListView();

        ImageButton newGame = (ImageButton) findViewById(R.id.imageButton);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DialogFragment newFragment = popup.newInstance();
                    newFragment.show(getFragmentManager(), "dialog");

            }
        });
    }

    private void populateListView(){
        dictionaryHelper = new DictionaryHelper(this);
        SQLiteDatabase db = dictionaryHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from GAMES", null);
        String[] fields = new String[] {"Opponent", "Date"};
        final SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, cursor,  fields, new int[] {android.R.id.text1,android.R.id.text2},0);
        ListView listView = (ListView) findViewById(R.id.notifications_list);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                int gameID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
                Log.d("Position", Integer.toString(gameID));

                Intent i = new Intent(getBaseContext(), CourtActivity.class);
                i.putExtra("gameID", gameID);
                startActivity(i);
            }
        });
    }


}

