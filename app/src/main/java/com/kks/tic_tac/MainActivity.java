package com.kks.tic_tac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red;

    int activePlayer = 0;
    boolean gameIsActive = true;
    //2=unplayed
    int [] gamestate = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedCounter] == 2 && gameIsActive) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int [] winningPosition : winningPositions)
            {
                if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]] &&
                        gamestate[winningPosition[1]]==gamestate[winningPosition[2]] &&
                        gamestate[winningPosition[0]]!=2)
                {
                    gameIsActive = false;
                    String winner = "RED";
                    if(gamestate[winningPosition[0]]==0)
                    {
                        winner = "YELLOW";
                    }
                    TextView  won = (TextView)findViewById(R.id.won);
                    won.setText(winner + " has won!");
                    final LinearLayout layout = (LinearLayout)findViewById(R.id.PlayAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameIsOver = true;
                    for (int counterState : gamestate)
                    {
                       if(counterState ==2) gameIsOver = false;
                    }
                    if(gameIsOver)
                    {
                        TextView  won = (TextView)findViewById(R.id.won);
                        won.setText("Its a draw");
                        final LinearLayout layout = (LinearLayout)findViewById(R.id.PlayAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view)
    {
        final LinearLayout layout = (LinearLayout)findViewById(R.id.PlayAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        gameIsActive = true;
        activePlayer = 0;
        for(int i =0;i< gamestate.length;i++)
        {
            gamestate[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i= 0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}