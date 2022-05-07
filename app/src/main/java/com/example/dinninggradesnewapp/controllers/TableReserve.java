package com.example.dinninggradesnewapp.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dinninggradesnewapp.R;

public class TableReserve extends AppCompatActivity {
    public Button move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reserve);

        move = findViewById(R.id.submit);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableReserve.this, ReservedTable.class);
                startActivity(intent);
            }
        });
    }

}
