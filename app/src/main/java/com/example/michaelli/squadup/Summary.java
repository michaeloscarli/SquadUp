package com.example.michaelli.squadup;
import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.lang.String;

/**
 * Created by jackie on 4/4/2016
 */

/*change to database name*/
public static final String TABLE_OUTLET = "tbloutletdata";
public static final String TABLE_NAME = "SquadUp";

public class Summary extends Activity {

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.summary);

        playerID   = (EditText) findViewById(R.id.playerID);
        gameDate   = (EditText) findViewById(R.id.gameDate);
        teamA      = (EditText) findViewById(R.id.teamA);
        teamB      = (EditText) findViewById(R.id.teamB);
        teamAQ1    = (EditText) findViewById(R.id.teamAQ1);
        teamAQ2    = (EditText) findViewById(R.id.teamAQ2);
        teamAQ3    = (EditText) findViewById(R.id.teamAQ3);
        teamAQ4    = (EditText) findViewById(R.id.teamAQ4);
        teamATotal = (EditText) findViewById(R.id.teamATotal);
        teamBQ1    = (EditText) findViewById(R.id.teamBQ1);
        teamBQ2    = (EditText) findViewById(R.id.teamBQ2);
        teamBQ3    = (EditText) findViewById(R.id.teamBQ3);
        teamBQ4    = (EditText) findViewById(R.id.teamBQ4);
        teamBTotal = (EditText) findViewById(R.id.teamBTotal);
        FT         = (EditText) findViewById(R.id.FT);
        twoPT      = (EditText) findViewById(R.id.twoPT);
        threePT    = (EditText) findViewById(R.id.threePT);
        totalPTS   = (EditText) findViewById(R.id.totalPTS);


    }

    protected void getSummaryData(SQLiteDatabase db, int outlet_id) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            String category = cursor.getString(cursor
                    .getColumnIndexOrThrow(ContactsDB.COLUMN_CATEGORY));

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_OUTLET;

        String playerID = cursor.getString(cursor.getColumnIndex("playerID"));
        String gameDate = cursor.getString(cursor.getColumnIndex("datetime"));
        int outlet_id = cursor.getInt(cursor.getColumnIndex("outlet_id"));

            /*STEP INTO THIS TABLE*/
        String teamA = cursor.getString(cursor.getColumnIndex("teamA"));
        String teamB = cursor.getString(cursor.getColumnIndex("teamB"));
        String teamAQ1 = cursor.getString(cursor.getColumnIndex("teamAQ1"));
        String teamBQ1 = cursor.getString(cursor.getColumnIndex("teamBQ1"));
        String teamAQ2 = cursor.getString(cursor.getColumnIndex("teamAQ2"));
        String teamBQ2 = cursor.getString(cursor.getColumnIndex("teamBQ2"));
        String teamAQ3 = cursor.getString(cursor.getColumnIndex("teamAQ3"));
        String teamBQ3 = cursor.getString(cursor.getColumnIndex("teamBQ3"));
        String teamAQ4 = cursor.getString(cursor.getColumnIndex("teamAQ4"));
        String teamBQ4 = cursor.getString(cursor.getColumnIndex("teamBQ4"));
        String teamATotal = cursor.getString(cursor.getColumnIndex("teamATotal"));
        String teamBTotal = cursor.getString(cursor.getColumnIndex("teamBTotal"));

        String FT = cursor.getString(cursor.getColumnIndex("FT"));
        String twoPT = cursor.getString(cursor.getColumnIndex("twoPT"));
        String threePT = cursor.getString(cursor.getColumnIndex("threePT"));
        String totalPTS = cursor.getString(cursor.getColumnIndex("totalPTS"));

    }


}