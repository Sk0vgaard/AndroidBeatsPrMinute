package com.example.skovgaard.bpm;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    TextView mBeats, mBeatsPrMinute, mCountdown;
    Button mBeatBtn;

    private int beatCount = 0;
    private double numberOfBeatsPrMinute;

    private CountDownTimer cTimer = null;
    private boolean isStarted = false;

    long startTime = 0;


    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            seconds = seconds % 60;

            mBeatsPrMinute.setText(String.format("%02d", seconds));

            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBeats = findViewById(R.id.beats);
        mBeatsPrMinute = findViewById(R.id.beatsPrMinut);
        mCountdown = findViewById(R.id.countdown);

        mBeatBtn = findViewById(R.id.beatBtn);

        mBeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                beatCounter();
                startTimer();
                beatsPerMinute();

            }
        });
    }

    private void beatCounter() {
        beatCount++;
        mBeats.setText("Beats: " + beatCount);
    }

    private void startTimer() {
        cTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                mCountdown.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mCountdown.setText("Done!");
            }
        };
        if (!isStarted){
            cTimer.start();
            startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            isStarted = true;
        }

    }


        private void beatsPerMinute() {

//            int seconds = Integer.valueOf(mBeatsPrMinute.getText().toString());
//            System.out.println(seconds);
//        numberOfBeatsPrMinute = beatCount/startTime;
//        mBeatsPrMinute.setText("Beats Per Minute: " + String.valueOf(numberOfBeatsPrMinute));

    }

};
