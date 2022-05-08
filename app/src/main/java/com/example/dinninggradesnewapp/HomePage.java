package com.example.deliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {
public Button btn_ok;
public ImageView img_1;
public ImageView img_11;
public Button btn_ok1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);

        btn_ok=(Button) findViewById(R.id.btn_2);
        img_1=(ImageView) findViewById(R.id.imageView6);
btn_ok1=(Button)findViewById(R.id.btn_3);
img_11=(ImageView) findViewById(R.id.imageView7);

btn_ok.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
        Intent intent=new Intent(HomePage.this,DeliveryDetails.class);
        intent.putExtra("resId",R.drawable.rices);
        startActivity(intent);
    }
});

        btn_ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this,DeliveryDetails.class);
                intent.putExtra("resId1",R.drawable.frice);
                startActivity(intent);
            }
        });

    }

}