package com.example.electricvehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.example.electricvehicle.databinding.ActivityAfterMainBinding;
import com.example.electricvehicle.databinding.ActivityMainBinding;

public class AfterMainActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ActivityAfterMainBinding binding;
    private String[] items = {"Ankara", "İstanbul", "Eskişehir", "Antalya", "İzmir"};
    private Intent chargeItIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAfterMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(binding.getRoot());

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        binding.listview.setAdapter(adapter);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = items[position];
                Intent intent = new Intent(AfterMainActivity.this, ChargeItActivity.class);
                intent.putExtra("itemTitle", clickedItem);
                startActivity(intent);
            }
        });

    }

}