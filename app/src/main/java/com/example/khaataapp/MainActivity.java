package com.example.khaataapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    Button btnAddNewKhatta_m, btnUpdateKhatta_m, btnDeleteKhatta_m, btnViewAllKhattas_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnAddNewKhatta_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddNewKhattaActivity.class);
                startActivity(intent);

            }
        });

        btnUpdateKhatta_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);


            }
        });

        btnDeleteKhatta_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DeleteKhattaActivity .class);
                startActivity(intent);

            }
        });

        btnViewAllKhattas_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ViewAllActivity.class);
                startActivity(intent);

            }
        });



    }


    private void init(){

        btnAddNewKhatta_m = findViewById(R.id.btnAddNewKhatta_m);
        btnDeleteKhatta_m = findViewById(R.id.btnDeleteKhatta_m);
        btnUpdateKhatta_m = findViewById(R.id.btnUpdateKhatta_m);
        btnViewAllKhattas_m = findViewById(R.id.btnViewAllKhattas_m);

    }
}