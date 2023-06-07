package com.example.electricvehicle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electricvehicle.databinding.ActivityAfterMainBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.example.electricvehicle.databinding.ActivityMainBinding;
import com.example.electricvehicle.databinding.ActivityChargeItBinding;
import com.example.electricvehicle.databinding.ActivityPayBinding;

public class ChargeItActivity extends AppCompatActivity {

    private ActivityChargeItBinding binding;
    private ImageButton backButton;
    private Intent afterMainIntent;
    private double Ac=6.18, Dc=7.32;
    public Integer Dk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChargeItBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.progressBar.setVisibility(View.INVISIBLE); // ProgressBar'ı gizle
        binding.loadingText.setVisibility(View.INVISIBLE); // Text'i gizle

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSarjEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE); // ProgressBar'ı görünür hale getir
                binding.loadingText.setVisibility(View.VISIBLE); // Text'i görünür hale getir
                Dk = Integer.parseInt(binding.edtTime.getEditText().getText().toString());
                if(binding.edtSocket.getSelectedItem().toString().equals("AC")){
                    PayActivity.activityPay = Dk*Ac;
                }else{
                    PayActivity.activityPay = Dk*Dc;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressBar.setVisibility(View.INVISIBLE); // ProgressBar'ı gizle
                        binding.loadingText.setVisibility(View.INVISIBLE); // Text'i gizle
                        afterMainIntent = new Intent(ChargeItActivity.this, PayActivity.class);
                        startActivity(afterMainIntent);
                        LoadingActivity.payMin = Dk;
                    }
                }, 2000); // 2 saniye (5000 milisaniye) beklet

            }
        });



    }
}
