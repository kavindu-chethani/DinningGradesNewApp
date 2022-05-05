package com.example.dinninggradesnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sign_upActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this,loginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    public void onRegisterClick(View view)
    {
        startActivity(new Intent(this, com.example.dinninggradesnewapp.paymentActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }



}