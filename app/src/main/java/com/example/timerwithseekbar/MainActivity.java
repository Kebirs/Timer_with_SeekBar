package com.example.timerwithseekbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;


public class MainActivity extends AppCompatActivity {

    public SeekBar seekBar;
    private TextView countDownText;
    private Button countDownButton;
    private CountDownTimer timer;

    private long timeLeft = 600000;
    private long secInterval = 1000;
    int progress = 10;
    int max = 600;

    String text = "Time Finish!";

    private boolean timeRunning;

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        countDownText.setText(MessageFormat.format("{0}:{1}", minutes, seconds));


    }

    public void startStop() {
        if (timeRunning) {
            stopTimer();
        } else {
            startTimer();
        }

    }

    public long getTimeLeft() {
        timeLeft = seekBar.getProgress() * 1000;
        return timeLeft;
    }

    public void startTimer() {
        timer = new CountDownTimer(timeLeft + 50, secInterval) {
            @Override
            public void onTick(long l) {
                timeLeft = getTimeLeft();
                updateTimer((int) l / 1000);
                seekBar.setProgress((int) l / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
            }
        }.start();

        countDownButton.setText(R.string.pause);
        timeRunning = true;
    }

    public void stopTimer() {
        timer.cancel();
        countDownButton.setText(R.string.start);
        timeRunning = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownText = findViewById(R.id.textView);
        countDownButton = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);


        seekBar.setProgress(progress);
        seekBar.setMax(max);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
                getTimeLeft();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
    }

}
































