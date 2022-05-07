package com.example.dinninggradesnewapp.controllers;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.dinninggradesnewapp.R;

public class HomeActivity extends AppCompatActivity {
    public Button move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        move = findViewById(R.id.reserve);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TableView.class);
                startActivity(intent);
            }
        });
    }
}