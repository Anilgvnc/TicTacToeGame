package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //cross = 1, circle = 0;
    int activePlayer = 0;
    //if not played = 2;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winingPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

    public void makeToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        System.out.print(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

            for(int[] winingPosition : winingPositions){
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] &&
                        gameState[winingPosition[1]] == gameState[winingPosition[2]] &&
                        gameState[winingPosition[0]] != 2){

                    String winner = "Cross";

                    if (gameState[winingPosition[0]] == 1){
                        winner = "Circle";
                    }

                    TextView winnerMessage = findViewById(R.id.winnerText);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }
            }
        }

    }

    MediaPlayer mediaPlayer;
    int click = 0;

    public void audioStart(View view){
        if(click == 0){
            mediaPlayer.start();
            makeToast("Music starts.");
            click =1;
        }
        else{
            mediaPlayer.pause();
            makeToast("Music paused.");
            click =0;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.shreksophone);
    }
}