package com.example.dinninggradesnewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableUserDetails extends AppCompatActivity {


        public Button move;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_table_user_details);
            move = findViewById(R.id.submit);
            move.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TableUserDetails.this, TableReserve.class);
                    startActivity(intent);
                }
            });
        }
    }