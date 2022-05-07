package com.example.dinninggradesnewapp.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dinninggradesnewapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TableUserDetails extends AppCompatActivity {
        public Button move;
    EditText name,contactno,nic,email;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_user_details);

        name=(EditText)findViewById(R.id.txtname);
        contactno=(EditText)findViewById(R.id.txtContactNo);
        nic=(EditText)findViewById(R.id.txtNic);
        email=(EditText)findViewById(R.id.txtEmail);

        move=(Button)findViewById(R.id.submit);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert() {
        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("contactno",contactno.getText().toString());
        map.put("nic",nic.getText().toString());
        map.put("email",email.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("tableUserDetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        contactno.setText("");
                        nic.setText("");
                        email.setText("");
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