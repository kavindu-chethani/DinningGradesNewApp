package com.example.dinninggradesnewapp.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.dinninggradesnewapp.R;

public class TableView extends AppCompatActivity {
    public Button move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        move = findViewById(R.id.user);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableView.this, TableUserDetails.class);
                startActivity(intent);
            }
        });
    }
}