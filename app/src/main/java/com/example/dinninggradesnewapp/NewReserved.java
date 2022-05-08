package com.example.dinninggradesnewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewReserved extends AppCompatActivity {

    public Button move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserved);

        move = findViewById(R.id.usubmit);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewReserved.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}