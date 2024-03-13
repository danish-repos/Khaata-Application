package com.example.khaataapp;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class AddNewKhattaActivity extends AppCompatActivity {


    TextInputEditText etTitle_a, etDescription_a, etDate_a, etPrice_a;
    Button btnAddKhatta_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_khatta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        init();

        KhattaDB db = new KhattaDB(this);

        btnAddKhatta_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle_a.getText().toString().trim();
                String description = etDescription_a.getText().toString().trim();
                String date = etDate_a.getText().toString().trim();
                String price = etPrice_a.getText().toString().trim();


                if(title.isEmpty() || description.isEmpty() || date.isEmpty() || price.isEmpty()){

                    Toast.makeText(AddNewKhattaActivity.this, "You can't leave anything empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    db.open();
                    db.addNewKhatta(title, description, date, price);
                    db.close();

                }catch (SQLException e){

                    Toast.makeText(AddNewKhattaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(AddNewKhattaActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                etTitle_a.setText("");
                etDescription_a.setText("");
                etDate_a.setText("");
                etPrice_a.setText("");


            }
        });

    }

    private void init(){
        etTitle_a = findViewById(R.id.etTitle_a);
        etDescription_a = findViewById(R.id.etDescription_a);
        etDate_a = findViewById(R.id.etDate_a);
        etPrice_a = findViewById(R.id.etPrice_a);


        btnAddKhatta_a = findViewById(R.id.btnAddKhatta_a);
    }
}