package com.example.dinninggradesnewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TableUserDetails extends AppCompatActivity {
        public Button button1;
        public Button button2;
    EditText name,contactno,nic,email;
    boolean isAllFieldsChecked = false;
//Insert function
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_user_details);

        name=(EditText)findViewById(R.id.txtname);
        contactno=(EditText)findViewById(R.id.txtContactNo);
        nic=(EditText)findViewById(R.id.txtNic);
        email=(EditText)findViewById(R.id.txtEmail);

        button1=(Button)findViewById(R.id.submit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked) {
                    processinsert();
                }
            }
        });

        button2=(Button)findViewById(R.id.next);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableUserDetails.this, TableReserve.class);
                startActivity(intent);
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
    // function which checks all the text fields
    // are filled or not by the user.
    // when user clicks on the PROCEED button
    // this function is triggered.
    private boolean CheckAllFields() {
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }

        if (contactno.length() == 0) {
            contactno.setError("contactno is required");
            return false;
        }

        if (nic.length() == 0) {
            nic.setError("NIC is required");
            return false;
        }

        if (email.length() == 0) {
            email.setError("Email is required");
            return false;

        }else
            // after all validation return true.
            return true;
        }


}