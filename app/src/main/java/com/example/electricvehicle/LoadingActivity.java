package com.example.electricvehicle;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LoadingActivity extends AppCompatActivity {

    public static boolean boolMinutes = false;
    public static Integer payMin;
    private Button btnOk;
    private ProgressBar pbTimer;
    private TextView tvTimerLeft;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        btnOk = findViewById(R.id.btnOk);
        pbTimer = findViewById(R.id.pbTimer);
        tvTimerLeft = findViewById(R.id.tvTimerLeft);
        //btnPay = findViewById();



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int timeInMinutes = payMin * 60;
                    startTimer(timeInMinutes);
            }
        });
    }

    private void startTimer(int timeInMinutes) {
        timeLeftInMillis = timeInMinutes * 1000;

        countDownTimer = new CountDownTimer(timeLeftInMillis, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Timer finished, perform desired actions
            }
        };

        countDownTimer.start();
    }

    private void updateTimer() {
        int totalSeconds = (int) (timeLeftInMillis / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // Update TextView to display remaining time in minutes and seconds
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimerLeft.setText(timeLeft);

        // Update ProgressBar to reflect the remaining time
        int maxProgress = (int) (timeLeftInMillis / COUNTDOWN_INTERVAL);
        int currentProgress = maxProgress - (int) (timeLeftInMillis / 1000);
        pbTimer.setMax(maxProgress);
        pbTimer.setProgress(currentProgress);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
