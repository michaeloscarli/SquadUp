package com.example.michaelli.squadup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michaelli on 3/29/16.
 */
public class DictionaryHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    private static final String DATABASE_NAME = "SquadUp";
    private static final String DICTIONARY_GAMES_CREATE =
            "CREATE TABLE GAMES " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Opponent varchar(255), " +
                    "HomeQ1 int default 0, " +
                    "HomeQ2 int default 0, " +
                    "HomeQ3 int default 0, " +
                    "HomeQ4 int default 0, " +
                    "HomeScore int default 0, " +
                    "OpponentQ1 int default 0, " +
                    "OpponentQ2 int default 0, " +
                    "OpponentQ3 int default 0, " +
                    "OpponentQ4 int default 0, " +
                    "OpponentScore int default 0, " +
                    "PlayerMadeFG int default 0, " +
                    "PlayerTotalFG int default 0, " +
                    "PlayerMade3PT int default 0, " +
                    "PlayerTotal3PT int default 0, " +
                    "PlayerTotalPTS int default 0, " +
                    "Date datetime, " +
                    "Complete bool default false," +
                    "Win bool default false);";

    private static final String DICTIONARY_PLAYER_CREATE =
            "CREATE TABLE PLAYERS " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "points int, " +
                    "numGames int);";

    DictionaryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_GAMES_CREATE);
        db.execSQL(DICTIONARY_PLAYER_CREATE);
        Log.d("Dictionary Helper", "Creating database");

    }

//    public void insertGame(String Opponent) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.beginTransaction();
//        String sql = "INSERT INTO GAMES (Opponent, Date) " +
//                "VALUES('" + Opponent + "'," + "datetime());";
//        db.execSQL(sql);
//        db.setTransactionSuccessful();
//        db.endTransaction();
//    }

    public void insertGame(String opponent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();

        values.put("Opponent", opponent);
        values.put("Date", dateFormat.format(date));

        db.insert("GAMES", null, values);
        db.close();
//
//
//
//        String sql = "INSERT INTO GAMES (Opponent, Date) " +
//                "VALUES('" + opponent + "'," + "datetime());";
//        db.execSQL(sql);
//        db.setTransactionSuccessful();
//        db.endTransaction();
    }

    public void updateGame(ContentValues values, int gameID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update("GAMES", values, "_id=" + gameID, null);
        db.close();
    }

//    public void insertNewGame(String Opponent, int PlayerID, int HomeScore, int OpponentScore, int win){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.beginTransaction();
//        db.execSQL("INSERT INTO GAMES (Opponent, PlayerID, HomeScore, OpponentScore, Date, Win) VALUES('" + Opponent + "'," + PlayerID + "," + HomeScore + "," + OpponentScore + ", datetime()," + win + ");");
//        db.setTransactionSuccessful();
//        db.endTransaction();
//    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }

    public Cursor getSelectCursor(String query)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.d("DEBUG", cursor.getString(0) + " " + cursor.getString((cursor.getColumnIndex("HomeScore"))));
            }
        }
        return db.rawQuery(query, null);
    }

}