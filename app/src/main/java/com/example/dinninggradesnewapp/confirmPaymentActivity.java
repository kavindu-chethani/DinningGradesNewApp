package com.example.dinninggradesnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class confirmPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);
        EditText edit_name = findViewById(R.id.editTextOwnerName);
        EditText edit_cardNo = findViewById(R.id.editTextCardNumber);
        EditText edit_expDate = findViewById(R.id.editTextExpireDate);
        EditText edit_cvv = findViewById(R.id.editTextCVV);
        EditText edit_bank = findViewById(R.id.editTextBank);
        Button btn_payNow = findViewById(R.id.cirPayNowButton);
    }







   /* public void onPayNowClick(View view)
    {
        startActivity(new Intent(this,#.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }*/

    public void onCardDetailsClick(View view)
    {
        startActivity(new Intent(this, com.example.dinninggradesnewapp.paymentCardActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}