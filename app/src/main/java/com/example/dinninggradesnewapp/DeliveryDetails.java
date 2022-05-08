package com.example.deliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DeliveryDetails extends AppCompatActivity {
ImageView img_2;
//ImageView img_3;
    Button btn_deliver;
    Button toastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliverydetails);

        img_2 = (ImageView)findViewById(R.id.imageView3);
        btn_deliver=(Button)findViewById(R.id.btn_5);
toastButton=(Button)findViewById(R.id.btn_6);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null)

        {
            int resId = bundle.getInt("resId");
            img_2.setImageResource(resId);

        }



        btn_deliver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeliveryDetails.this, EditDetails.class);
                startActivity(intent);
            }


        });
        toastButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(DeliveryDetails.this,"Order Cancel",Toast.LENGTH_LONG).show();
            }
        });

    }
}