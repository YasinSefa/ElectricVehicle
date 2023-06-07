package com.example.electricvehicle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.electricvehicle.Connect.Connection;
import com.example.electricvehicle.Connect.Interface;
import com.example.electricvehicle.Connect.Response;
import com.example.electricvehicle.databinding.ActivityMainBinding;
import com.example.electricvehicle.databinding.ActivityPayBinding;
import com.example.electricvehicle.databinding.ActivitySignUpBinding;
import retrofit2.Call;
import retrofit2.Callback;

public class SignUpActivity extends AppCompatActivity {

    private Intent afterMainIntent;

    private Intent signUpIntent;
    private ActivitySignUpBinding binding;
    private Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.edtSignUpFullName.getEditText().getText().toString().equals(""))
                {
                    binding.edtSignUpFullName.setError("Adınızı ve soyadınızı giriniz.");
                }
                else if(binding.edtSignUpEmail.getEditText().getText().toString().equals(""))
                {
                    binding.edtSignUpEmail.setError("E mailinizi giriniz.");
                } else if (binding.edtSignUpMobile.getEditText().getText().toString().equals(""))
                {
                    binding.edtSignUpMobile.setError("Telefon numaranızı giriniz.");
                } else if (binding.edtSignUpPassword.getEditText().getText().toString().equals("")) {
                    binding.edtSignUpPassword.setError("Şifrenizi giriniz.");
                } else if (binding.edtSignUpConfirmPassword.getEditText().getText().toString().equals("")) {
                    binding.edtSignUpConfirmPassword.setError("Şifrenizi giriniz.");
                } else if (!binding.edtSignUpPassword.getEditText().getText().toString().equals(binding.edtSignUpConfirmPassword.getEditText().getText().toString())) {
                    binding.edtSignUpPassword.setError("Şifreler uyuşmuyor.");
                    binding.edtSignUpConfirmPassword.setError("Şifreler uyuşmuyor.");
                } else
                {
                    AddData();
                }
            }
        });
    }

    public void AddData(){
        //Şu alta şu şekilde yazıcaksın binding.||username||.getEditText().getText().toString() şu çubuklar arasındaki
        // username kısmına ismini değişip aşşağıcaki kısma yapıştıracaksın.

        Call<Response> call= Connection.getConnectionAdduser().create(Interface.class).adduser(
                binding.edtSignUpFullName.getEditText().getText().toString()
                ,binding.edtSignUpEmail.getEditText().getText().toString()
                ,binding.edtSignUpPassword.getEditText().getText().toString()
                ,binding.edtSignUpMobile.getEditText().getText().toString()
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
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Kayıt Başarılı")
                                    .setIcon(android.R.drawable.ic_secure)
                                    .setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(SignUpActivity.this,"Kayıt Yapıldı", Toast.LENGTH_SHORT).show();


                                        }
                                    })
                                    .show();
                            mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                        else
                        {
                            new AlertDialog.Builder(SignUpActivity.this)
                                    .setTitle("Kayıt Başarısız")
                                    .setIcon(android.R.drawable.ic_secure)
                                    .setPositiveButton("Devam Et", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(SignUpActivity.this,"Kayıt Yapılamadı", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(SignUpActivity.this, "Error 400", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}

