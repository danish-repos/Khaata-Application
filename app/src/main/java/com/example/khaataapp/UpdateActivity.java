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

public class UpdateActivity extends AppCompatActivity {

    TextInputEditText etID_u, etTitle_u, etDescription_u, etDate_u, etPrice_u;
    Button btnUpdate_u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        init();
        KhattaDB db = new KhattaDB(this);
        
        btnUpdate_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = etID_u.getText().toString().trim();
                String title = etTitle_u.getText().toString().trim();
                String description = etDescription_u.getText().toString().trim();
                String date = etDate_u.getText().toString().trim();
                String price = etPrice_u.getText().toString().trim();

                if(ID.isEmpty() || title.isEmpty() || description.isEmpty() || date.isEmpty() || price.isEmpty()){

                    Toast.makeText(UpdateActivity.this, "You can't leave anything empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    db.open();

                    int linesAffected = db.updateKhatta(ID, title, description, date, price);

                    db.close();

                    if (linesAffected == 0)
                    {
                        Toast.makeText(UpdateActivity.this, "ID not found", Toast.LENGTH_SHORT).show();

                        etID_u.setText("");

                        return;
                    }
                }catch (SQLException e){

                    Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(UpdateActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void init(){
        etTitle_u = findViewById(R.id.etTitle_u);
        etDescription_u = findViewById(R.id.etDescription_u);
        etDate_u = findViewById(R.id.etDate_u);
        etPrice_u = findViewById(R.id.etPrice_u);


        btnUpdate_u = findViewById(R.id.btnUpdate_u);
    }
}