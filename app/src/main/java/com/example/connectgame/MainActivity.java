package com.example.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2 , 2 , 2 , 2 , 2 , 2 , 2 , 2 , 2};

    int[][] winnningPositions ={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        counter.getTag();

        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedcounter] == 2 && gameActive){

        gameState[tappedcounter] = activePlayer;

        counter.setTranslationY(-1500);

        if (activePlayer == 1){
            counter.setImageResource(R.drawable.temple_ton);

            activePlayer = 0;
        }else {
            counter.setImageResource(R.drawable.boss_baby);

            activePlayer = 1;
        }


        counter.animate().translationYBy(1500).rotation(36000).setDuration(300);

        for (int[] winningPosition : winnningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){

                gameActive = false;

                String winner = "";

                if (activePlayer == 1){
                    winner = "bossBaby";
                }else{
                    winner = "templeTon";
                }


                Button playAgain = (Button) findViewById(R.id.playAgain);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                winnerTextView.setText(winner + " has won");

                playAgain.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
            }
        } }
    }
    public  void playAgain(View view){


        Button playAgain = (Button) findViewById(R.id.playAgain);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgain.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

            for(i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }

            activePlayer = 0;
            gameActive = true;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}