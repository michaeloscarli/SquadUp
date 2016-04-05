package com.example.michaelli.squadup;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.bailey.mobile.squadup.R;

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
        dictionaryHelper.insertGame("Jason");
        SQLiteDatabase db = dictionaryHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM GAMES;",null);
        populateListView();
    }

    private void populateListView(){
        dictionaryHelper = new DictionaryHelper(this);
        SQLiteDatabase db = dictionaryHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from GAMES", null);
        String[] fields = new String[] {"Opponent", "Date"};
        SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, cursor,  fields, new int[] { android.R.id.text1,android.R.id.text2},0);
        ListView listView = (ListView) findViewById(R.id.notifications_list);
        listView.setAdapter(simpleCursorAdapter );
    }
}
