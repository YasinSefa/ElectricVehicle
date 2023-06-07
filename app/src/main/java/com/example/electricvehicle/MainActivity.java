package com.example.electricvehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.electricvehicle.Connect.Connection;
import com.example.electricvehicle.Connect.Interface;
import com.example.electricvehicle.Connect.Response;
import com.example.electricvehicle.databinding.ActivityMainBinding;



import retrofit2.Call;
import retrofit2.Callback;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Intent afterMainIntent;
    private Intent signUpIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.username.getEditText().getText().toString().equals(""))
                {
                    binding.username.setError("E postanızı giriniz.");
                }
                else if(binding.password.getEditText().getText().toString().equals(""))
                {
                    binding.password.setError("Şifrenizi giriniz");
                }else
                {
                    afterMainIntent = new Intent(MainActivity.this, AfterMainActivity.class);
                    startActivity(afterMainIntent);
                   // CheckData();
                }
            }


        });

        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

    }

    public void CheckData(){
        Call<Response> call= Connection.getConnectionCheckuser().create(Interface.class).checkuser(
                binding.username.getEditText().getText().toString(),
                binding.password.getEditText().getText().toString()

        );
        //listen response  from  response class
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                //listen response

                //listen server

                if(response.code()==200)
                {
                    if(response.body().getStatus().equals("ok"))
                    {
                        if(response.body().getResult_code()==1)
                        {

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Giriş Başarılı")
                                    .setIcon(android.R.drawable.ic_secure)
                                    .setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MainActivity.this,"Giriş Yapıldı", Toast.LENGTH_SHORT).show();

                                            afterMainIntent = new Intent(MainActivity.this, AfterMainActivity.class);
                                            startActivity(afterMainIntent);

                                        }
                                    })
                                    .show();

                        }
                        else
                        {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Giriş Başarısız")
                                    .setIcon(android.R.drawable.ic_secure)
                                    .setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MainActivity.this,"Giriş Yapılamadı", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .show();

                            System.out.println("haram");

                        }
                    }
                }
                else
                {
                    //no db connection
                    Toast.makeText(MainActivity.this, "Error 400", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                System.out.println(t.toString());

            }
        });
    }






}