package com.example.michaelli.squadup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bailey.mobile.squadup.R;

/**
 * Created by Jason on 4/4/2016.
 */
public class CourtActivity extends Activity{

    private int quarter;

    private int madeFG;
    private int totalFG;
    private int made3PT;
    private int total3PT;
    private int madeFT;
    private int totalFT;
    private int totalPTS;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_actions);
        newGame();
    }

    private void newGame()
    {
        quarter = 1;

        madeFG = 0;
        totalFG = 0;
        made3PT = 0;
        total3PT = 0;
        totalPTS = 0;

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
    }

    public void shotTaken(boolean madeShot, boolean isThree)
    {
        if (madeShot && isThree)
        {
            made3PT++;
            total3PT++;
            madeFG++;
        }
        else if (madeShot)
        {
            madeFG++;
        }
        else if (isThree)
        {
            total3PT++;
        }
        totalFG++;

        updateTotalPoints();
    }

    public void updateTotalPoints()
    {
        int made2PT = madeFG - made3PT;
        totalPTS = made2PT * 2 + made3PT * 3 + madeFT * 1;
    }

    public void updateScores(boolean teamA, int score)
    {
        TextView tv = (TextView) this.findViewById(R.id.teamAQ1);
        TextView tv2;
        int currQuarterScore;
        int currTotalScore;

        if (teamA)
        {
            tv2 = teamATotal;
            switch(quarter)
            {
                case 1:
                    tv = teamAQ1;
                    break;
                case 2:
                    tv = teamAQ2;
                    break;
                case 3:
                    tv = teamAQ3;
                    break;
                case 4:
                    tv = teamAQ4;
                    break;
            }
        }
        else
        {
            tv2 = teamBTotal;
            switch(quarter)
            {
                case 1:
                    tv = teamBQ1;
                    break;
                case 2:
                    tv = teamBQ2;
                    break;
                case 3:
                    tv = teamBQ3;
                    break;
                case 4:
                    tv = teamBQ4;
                    break;
            }
        }

        currQuarterScore = Integer.parseInt(tv.getText().toString());
        int newQuarterScore = currQuarterScore + score;
        tv.setText(Integer.toString(newQuarterScore));

        currTotalScore = Integer.parseInt(tv2.getText().toString());
        int newTotalScore = currTotalScore + score;
        tv2.setText(Integer.toString(newTotalScore));
    }

    public void endQuarter(View view) {
        Button b = (Button) view.findViewById(R.id.endQuarter);
        switch (quarter) {
            case 4:


//                TODO - END THE GAME


                Q4.setBackgroundResource(R.color.inactiveQuarter);
                teamAQ4.setBackgroundResource(R.color.inactiveQuarter);
                teamBQ4.setBackgroundResource(R.color.inactiveQuarter);

                T.setBackgroundResource(R.color.activeQuarter);
                teamATotal.setBackgroundResource(R.color.activeQuarter);
                teamBTotal.setBackgroundResource(R.color.activeQuarter);

                quarter++;
                break;


            case 3:
                b.setText("End Game");

                Q3.setBackgroundResource(R.color.inactiveQuarter);
                teamAQ3.setBackgroundResource(R.color.inactiveQuarter);
                teamBQ3.setBackgroundResource(R.color.inactiveQuarter);

                Q4.setBackgroundResource(R.color.activeQuarter);
                teamAQ4.setBackgroundResource(R.color.activeQuarter);
                teamBQ4.setBackgroundResource(R.color.activeQuarter);

                quarter++;
                break;
            case 2:
                Q2.setBackgroundResource(R.color.inactiveQuarter);
                teamAQ2.setBackgroundResource(R.color.inactiveQuarter);
                teamBQ2.setBackgroundResource(R.color.inactiveQuarter);

                Q3.setBackgroundResource(R.color.activeQuarter);
                teamAQ3.setBackgroundResource(R.color.activeQuarter);
                teamBQ3.setBackgroundResource(R.color.activeQuarter);

                quarter++;
                break;
            case 1:
                Q1.setBackgroundResource(R.color.inactiveQuarter);
                teamAQ1.setBackgroundResource(R.color.inactiveQuarter);
                teamBQ1.setBackgroundResource(R.color.inactiveQuarter);

                Q2.setBackgroundResource(R.color.activeQuarter);
                teamAQ2.setBackgroundResource(R.color.activeQuarter);
                teamBQ2.setBackgroundResource(R.color.activeQuarter);

                quarter++;
                break;
            default:
                quarter++;
                break;
        }
    }

    public void teamAPlus1(View view)
    {
        updateScores(true, R.integer.freeThrow);
    }

    public void teamBPlus1(View view)
    {
        updateScores(false, R.integer.freeThrow);
    }
}
