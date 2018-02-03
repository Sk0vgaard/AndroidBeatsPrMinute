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

    private int beatCount = 0, oneMinuteCountdown = 60;
    private double numberOfBeatsPrMinute;
    int Seconds, Minutes, MilliSeconds ;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;




    private CountDownTimer cTimer = null;
    private boolean isStarted = false;

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
        cTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mCountdown.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mCountdown.setText("Done!");
            }
        };
        if (!isStarted){
            cTimer.start();
            isStarted = true;
        }
//        handler.postDelayed(runnable, 0);

    }
//
//    public Runnable runnable = new Runnable() {
//
//        public void run() {
//            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
//
//            UpdateTime = TimeBuff + MillisecondTime;
//
//            Seconds = (int) (UpdateTime / 1000);
//
//            Minutes = Seconds / 60;
//
//            Seconds = Seconds % 60;
//
//            MilliSeconds = (int) (UpdateTime % 1000);
//
//            mBeatsPrMinute.setText("" + Minutes + ":"
//                    + String.format("%02d", Seconds) + ":"
//                    + String.format("%03d", MilliSeconds));
//
//            handler.postDelayed(this, 0);
//        }
//
//    };


        private void beatsPerMinute() {

        numberOfBeatsPrMinute = beatCount/oneMinuteCountdown;
        mBeatsPrMinute.setText("Beats Per Minute: " + String.valueOf(numberOfBeatsPrMinute));

    }

};
