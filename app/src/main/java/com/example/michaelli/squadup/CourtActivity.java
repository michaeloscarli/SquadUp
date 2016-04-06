package com.example.michaelli.squadup;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bailey.mobile.squadup.R;

public class CourtActivity extends Activity {

    private int quarter;

    private String opponentName;

    private TextView teamAQ1;
    private TextView teamAQ2;
    private TextView teamAQ3;
    private TextView teamAQ4;
    private TextView teamATotal;
    private TextView teamBQ1;
    private TextView teamBQ2;
    private TextView teamBQ3;
    private TextView teamBQ4;
    private TextView teamBTotal;

    private TextView Q1;
    private TextView Q2;
    private TextView Q3;
    private TextView Q4;
    private TextView T;

    private TextView FG;
    private TextView threePT;
    private TextView PTS;

    private TextView gameDate;

    private int homeScores[] = null;
    private int opponentScores[] = null;
    private int playerStats[] = null;
    /*
        playerStats[0] = madeFG
        playerStats[1] = totalFG
        playerStats[2] = made3PT
        playerStats[3] = total3PT
        playerStats[4] = totalPTS
     */

    private int gameID;
    private int complete;
    private int win;

    private String date;

    private DictionaryHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.capture_actions);

        Bundle passedData = getIntent().getExtras();
        if (passedData != null)
        {
            opponentName = passedData.getString("opponentName");
            if (opponentName != null)
            { //start a new game
                setContentView(R.layout.capture_actions);
                initializeGame();
                newGame(opponentName);
            }
            else
            { //old game - gameID is given, go to summary portrait view
                gameID = passedData.getInt("gameID");
                setContentView(R.layout.summary);
//                View backgroundimage = findViewById(R.id.summary_background);
//                Drawable background = backgroundimage.getBackground();
//                background.setAlpha(90);

                initializeGame();
                readDatabase();
            }
        }

        setReferencesInLayout();
        updateScoreBoard();

//        updateScoreBoard();

//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.summary);
//        View backgroundimage = findViewById(R.id.summary_background);
//        Drawable background = backgroundimage.getBackground();
//        background.setAlpha(80);

//        Button shareButton = (Button) findViewById(R.id.shareButton);
//        shareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getBaseContext(), mainEmail.class);
//                i.putExtra("gameID",gameID);
//                startActivity(i);
//            }
//        });
//        Button homeButton = (Button) findViewById(R.id.homeButton);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getBaseContext(), GameHistory.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }

    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig)
    {
        Log.d("DEBUG", Integer.toString(homeScores[0]));

        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this, "In landscape mode", Toast.LENGTH_SHORT).show();

            setContentView(R.layout.capture_actions);
        }
        else
        {
            Toast.makeText(this, "In portrait mode", Toast.LENGTH_SHORT).show();

            setContentView(R.layout.summary);

            readDatabase();
        }

        setReferencesInLayout();
        updateScoreBoard();
    }

    public void readDatabase()
    {
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GAMES WHERE _id=" + gameID, null);

        if(cursor.moveToFirst()){
            do{
                opponentName = cursor.getString(cursor.getColumnIndex("Opponent"));
                homeScores[0] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HomeQ1")));
                homeScores[1] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HomeQ2")));
                homeScores[2] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HomeQ3")));
                homeScores[3] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HomeQ4")));
                homeScores[4] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HomeScore")));
                opponentScores[0] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("OpponentQ1")));
                opponentScores[1] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("OpponentQ2")));
                opponentScores[2] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("OpponentQ3")));
                opponentScores[3] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("OpponentQ4")));
                opponentScores[4] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("OpponentScore")));
                playerStats[0] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PlayerMadeFG")));
                playerStats[1] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PlayerTotalFG")));
                playerStats[2] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PlayerMade3PT")));
                playerStats[3] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PlayerTotal3PT")));
                playerStats[4] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PlayerTotalPTS")));
                date = cursor.getString(cursor.getColumnIndex("Date"));
                complete = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Complete")));
                win = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Win")));
            }while(cursor.moveToNext());
        }
    }

    private void setReferencesInLayout()
    {
        teamAQ1 = (TextView) this.findViewById(R.id.teamAQ1);
        teamAQ2 = (TextView) this.findViewById(R.id.teamAQ2);
        teamAQ3 = (TextView) this.findViewById(R.id.teamAQ3);
        teamAQ4 = (TextView) this.findViewById(R.id.teamAQ4);
        teamATotal = (TextView) this.findViewById(R.id.teamATotal);
        teamBQ1 = (TextView) this.findViewById(R.id.teamBQ1);
        teamBQ2 = (TextView) this.findViewById(R.id.teamBQ2);
        teamBQ3 = (TextView) this.findViewById(R.id.teamBQ3);
        teamBQ4 = (TextView) this.findViewById(R.id.teamBQ4);
        teamBTotal = (TextView) this.findViewById(R.id.teamBTotal);

        Q1 = (TextView) this.findViewById(R.id.Q1);
        Q2 = (TextView) this.findViewById(R.id.Q2);
        Q3 = (TextView) this.findViewById(R.id.Q3);
        Q4 = (TextView) this.findViewById(R.id.Q4);
        T = (TextView) this.findViewById(R.id.T);

        FG = (TextView) this.findViewById(R.id.FG);
        threePT = (TextView) this.findViewById(R.id.threePT);
        PTS = (TextView) this.findViewById(R.id.totalPTS);

        gameDate = (TextView) this.findViewById(R.id.gameDate);
    }

    private void initializeGame()
    {
        quarter = 1;

        homeScores = new int[5];
        opponentScores = new int[5];
        playerStats = new int[5];

        for (int i = 0; i < 5; i++)
        {
            homeScores[i] = 0;
            opponentScores[i] = 0;
            playerStats[i] = 0;
        }

        complete = 0;
        win = 0;

        setReferencesInLayout();

        dh = new DictionaryHelper(this);
    }

    private void newGame(String opponentName)
    {
        Q1.setTypeface(null, Typeface.BOLD);
        teamAQ1.setTypeface(null, Typeface.BOLD);
        teamBQ1.setTypeface(null, Typeface.BOLD);

        dh.insertGame(opponentName);

        Cursor cursor = dh.getReadableDatabase().rawQuery("SELECT max(_id) FROM GAMES;", null);
        if (cursor != null && cursor.moveToFirst())
        {
            gameID = Integer.parseInt(cursor.getString(0));
        }

    }

    public void shotTaken(boolean madeShot, boolean isThree)
    {
        if (madeShot && isThree)
        {
            playerStats[0]++; //madeFG++;
            playerStats[1]++; //totalFG++;
            playerStats[2]++; //made3PT++;
            playerStats[3]++; //total3PT++;

        }
        else if (madeShot)
        {
            playerStats[0]++; //madeFG++;
            playerStats[1]++; //totalFG++;
        }
        else if (isThree)
        {
            playerStats[1]++; //totalFG++;
            playerStats[3]++; //total3PT++;
        }
        else
        {
            playerStats[1]++; //totalFG++;
        }

        updateTotalPoints();
    }

    public void updateTotalPoints()
    {
        int madeFG = playerStats[0];
        int made3PT = playerStats[2];
        int made2PT = playerStats[0] - playerStats[2]; //int made2PT = madeFG - made3PT;
        playerStats[4] = made2PT * 2 + made3PT * 3; //totalPTS = made2PT * 2 + made3PT * 3 + madeFT * 1;






        // CHANGE THIS IF ADDING FREE THROWS



    }




    public void updateScoreBoard()
    {
        teamAQ1.setText(Integer.toString(homeScores[0]));
        teamAQ2.setText(Integer.toString(homeScores[1]));
        teamAQ3.setText(Integer.toString(homeScores[2]));
        teamAQ4.setText(Integer.toString(homeScores[3]));
        teamATotal.setText(Integer.toString(homeScores[4]));
        teamBQ1.setText(Integer.toString(opponentScores[0]));
        teamBQ2.setText(Integer.toString(opponentScores[1]));
        teamBQ3.setText(Integer.toString(opponentScores[2]));
        teamBQ4.setText(Integer.toString(opponentScores[3]));
        teamBTotal.setText(Integer.toString(opponentScores[4]));

        /*
            playerStats[0] = madeFG
            playerStats[1] = totalFG
            playerStats[2] = made3PT
            playerStats[3] = total3PT
            playerStats[4] = totalPTS
         */
        if (FG != null)
        {
            String fgString = playerStats[0] + "/" + playerStats[1];
            FG.setText(fgString);
        }
        if (threePT != null)
        {
            String threePTString = playerStats[2] + "/" + playerStats[3];
            threePT.setText(threePTString);
        }
        if (PTS != null)
        {
            PTS.setText(Integer.toString(playerStats[4]));
        }
        if (gameDate != null)
        {
            gameDate.setText(date);
        }

        setQuarterBold();
    }

    public void setQuarterBold()
    {
        Q1.setTypeface(null, Typeface.NORMAL);
        Q2.setTypeface(null, Typeface.NORMAL);
        Q3.setTypeface(null, Typeface.NORMAL);
        Q4.setTypeface(null, Typeface.NORMAL);
        T.setTypeface(null, Typeface.NORMAL);
        teamAQ1.setTypeface(null, Typeface.NORMAL);
        teamAQ2.setTypeface(null, Typeface.NORMAL);
        teamAQ3.setTypeface(null, Typeface.NORMAL);
        teamAQ4.setTypeface(null, Typeface.NORMAL);
        teamATotal.setTypeface(null, Typeface.NORMAL);
        teamBQ1.setTypeface(null, Typeface.NORMAL);
        teamBQ2.setTypeface(null, Typeface.NORMAL);
        teamBQ3.setTypeface(null, Typeface.NORMAL);
        teamBQ4.setTypeface(null, Typeface.NORMAL);
        teamBTotal.setTypeface(null, Typeface.NORMAL);

        switch (quarter)
        {
            case 1:
                Q1.setTypeface(null, Typeface.BOLD);
                teamAQ1.setTypeface(null, Typeface.BOLD);
                teamBQ1.setTypeface(null, Typeface.BOLD);
                break;
            case 2:
                Q2.setTypeface(null, Typeface.BOLD);
                teamAQ2.setTypeface(null, Typeface.BOLD);
                teamBQ2.setTypeface(null, Typeface.BOLD);
                break;
            case 3:
                Q3.setTypeface(null, Typeface.BOLD);
                teamAQ3.setTypeface(null, Typeface.BOLD);
                teamBQ3.setTypeface(null, Typeface.BOLD);
                break;
            case 4:
                Q4.setTypeface(null, Typeface.BOLD);
                teamAQ4.setTypeface(null, Typeface.BOLD);
                teamBQ4.setTypeface(null, Typeface.BOLD);
                break;

            default:
                T.setTypeface(null, Typeface.BOLD);
                teamATotal.setTypeface(null, Typeface.BOLD);
                teamBTotal.setTypeface(null, Typeface.BOLD);
                break;
        }
    }

    public void updateDatabase() {
        ContentValues values = new ContentValues();
        values.put("HomeQ1", homeScores[0]);
        values.put("HomeQ2", homeScores[1]);
        values.put("HomeQ3", homeScores[2]);
        values.put("HomeQ4", homeScores[3]);
        values.put("HomeScore", homeScores[4]);
        values.put("OpponentQ1", opponentScores[0]);
        values.put("OpponentQ2", opponentScores[1]);
        values.put("OpponentQ3", opponentScores[2]);
        values.put("OpponentQ4", opponentScores[3]);
        values.put("OpponentScore", opponentScores[4]);
        values.put("PlayerMadeFG", playerStats[0]);
        values.put("PlayerTotalFG", playerStats[1]);
        values.put("PlayerMade3PT", playerStats[2]);
        values.put("PlayerTotal3PT", playerStats[3]);
        values.put("PlayerTotalPTS", playerStats[4]);
        values.put("Complete", complete);
        values.put("Win", win);
        dh.updateGame(values, gameID);
//
//
//        String query = "SELECT * FROM GAMES";
////        SQLiteDatabase db = dh.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor != null)
//        {
//            cursor.moveToFirst();
//
//            Log.d("DEBUG", Integer.toString(cursor.getCount()));
//            if (cursor.getCount() > 0)
//            {
//                do {
//                    for (int i = 0; i < cursor.getColumnCount(); i++)
//                    {
//                        Log.d("DEBUG", cursor.getString(i));
//                    }
//
//                } while (cursor.moveToNext());
//            }
//        }
//


    }

    public void updateInternalScores(boolean teamA, int score)
    {
        if (teamA)
        {
            homeScores[quarter-1] += score;
            homeScores[4] += score;
        }
        else
        {
            opponentScores[quarter-1] += score;
            opponentScores[4] += score;
        }

        updateScoreBoard();
        updateDatabase();
    }

    public void endQuarter(View view) {
        Button b = (Button) view.findViewById(R.id.endQuarter);
        quarter++;
        setQuarterBold();

        switch (quarter) {
            case 1:
            case 2:
            case 3:
                break;
            case 4:
                b.setText("End Game");
                break;
            default:

                complete = 1;
                if (homeScores[4] > opponentScores[4]) {
                    win = 1;
                }

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                setContentView(R.layout.summary);

                readDatabase();
                setReferencesInLayout();
                updateScoreBoard();
                break;
        }
    }

    public void goToHome(View view){
        Log.d("home", "going home");
        Intent i = new Intent(getBaseContext(), GameHistory.class);
        startActivity(i);
        finish();
    }

    public void emailGame(View view){
        Log.d("email", "going email");
        Intent i = new Intent(getBaseContext(), mainEmail.class);
        i.putExtra("gameID", gameID);
        startActivity(i);
    }

    public void teamAPlus1(View view)
    {
        Resources res = getResources();
        updateInternalScores(true, res.getInteger(R.integer.freeThrow));
    }

    public void teamBPlus1(View view)
    {
        Resources res = getResources();
        updateInternalScores(false, res.getInteger(R.integer.freeThrow));
    }
}
