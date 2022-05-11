package com.example.dinninggradesnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dinninggradesnewapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TableReserve extends AppCompatActivity {
    public Button button1, button2;
    EditText tableno,floor,noofguest,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reserve);

        tableno=(EditText)findViewById(R.id.txtTNo);
        floor=(EditText)findViewById(R.id.txtFloor);
        noofguest=(EditText)findViewById(R.id.txtNofGuest);
        date=(EditText)findViewById(R.id.txtdate);
        time=(EditText)findViewById(R.id.txttime);


        button1=(Button)findViewById(R.id.submit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
        button2=(Button)findViewById(R.id.next);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableReserve.this, ReservedTable.class);
                startActivity(intent);
            }
        });


    }
    private void processinsert() {
        Map<String,Object> map=new HashMap<>();
        map.put("tableno",tableno.getText().toString());
        map.put("floor",floor.getText().toString());
        map.put("noofguest",noofguest.getText().toString());
        map.put("date",date.getText().toString());
        map.put("time",time.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("tableReserve").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        tableno.setText("");
                        floor.setText("");
                        noofguest.setText("");
                        date.setText("");
                        time.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}
