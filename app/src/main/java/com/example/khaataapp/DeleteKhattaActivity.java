package com.example.khaataapp;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

public class DeleteKhattaActivity extends AppCompatActivity {



    TextInputEditText etID_d;
    Button btnDelete_d, btnDeleteAll_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_khatta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        init();
        KhattaDB db = new KhattaDB(this);

        btnDelete_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = etID_d.getText().toString().trim();

                if(ID.isEmpty()){

                    Toast.makeText(DeleteKhattaActivity.this, "You can't leave anything empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{

                    db.open();
                    int linesAffected = db.deleteKhatta(ID);
                    db.close();

                    if (linesAffected == 0)
                    {

                        Toast.makeText(DeleteKhattaActivity.this, "ID not found", Toast.LENGTH_SHORT).show();
                        etID_d.setText("");
                        return;
                    }

                }catch (SQLException e){
                    Toast.makeText(DeleteKhattaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(DeleteKhattaActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        btnDeleteAll_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();

                db.eraseAllKhattas();
                Toast.makeText(DeleteKhattaActivity.this, "All khattas deleted", Toast.LENGTH_SHORT).show();
                
                db.close();
                finish();

            }
        });

    }



    private void init(){

        etID_d = findViewById(R.id.etID_d);

        btnDelete_d = findViewById(R.id.btnDelete_d);
        btnDeleteAll_d = findViewById(R.id.btnDeleteAll_d);

    }
}