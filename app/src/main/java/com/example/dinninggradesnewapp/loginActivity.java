package com.example.dinninggradesnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this,sign_upActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}

