package com.example.electricvehicle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricvehicle.databinding.ActivityMainBinding;
import com.example.electricvehicle.databinding.ActivityPayBinding;

import java.text.DecimalFormat;

public class PayActivity extends AppCompatActivity {
    private ActivityPayBinding binding;
    public Button buttonPayText;
    public static double activityPay;
    private Intent loadingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.buttonPayText.setText("Ödenecek Tutar: " + String.valueOf(new DecimalFormat("####.##").format(activityPay))+" TL");

        binding.buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingIntent = new Intent(PayActivity.this, LoadingActivity.class);
                startActivity(loadingIntent);
            }
        });

    }
}
