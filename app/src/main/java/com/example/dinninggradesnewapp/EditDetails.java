package com.example.deliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditDetails extends AppCompatActivity {

    EditText t1, address, contact, qty;
    Button btn_save;

    Edit edit;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdetails);
t1=(EditText) findViewById(R.id.t1);
        address = (EditText) findViewById(R.id.edit_address);
        contact = (EditText) findViewById(R.id.edit_contact);
        qty = (EditText) findViewById(R.id.edit_qty);
        btn_save = (Button) findViewById(R.id.btn_7);

        edit = new Edit();
    }

    public void ClearControls() {
        t1.setText("");
        address.setText("");
        contact.setText("");
        qty.setText("");
    }
//add data
    public void CreateData(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Delivery");
        try {
            if(TextUtils.isEmpty(t1.getText().toString())){
                Toast.makeText(getApplicationContext(), "enterId", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(address.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(contact.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Contact Number", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(qty.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Quantity", Toast.LENGTH_SHORT).show();
            } else {
                edit.setID(t1.getText().toString().trim());
                edit.setDeliverAddress(address.getText().toString().trim());
                edit.setContact(Integer.parseInt(contact.getText().toString().trim()));
                edit.setQty(Integer.parseInt(qty.getText().toString().trim()));

                dbRef.push().setValue(edit);

                Toast.makeText(getApplicationContext(), "Data inserted success", Toast.LENGTH_SHORT).show();
                ClearControls();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "invalid number format", Toast.LENGTH_SHORT).show();
        }
    }
//show data
    public void show(View view) {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Delivery").child("D101");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    t1.setText(dataSnapshot.child("id").getValue().toString());
                    address.setText(dataSnapshot.child("DeliveryAddress").getValue().toString());
                    contact.setText(dataSnapshot.child("contact").getValue().toString());
                    qty.setText(dataSnapshot.child("qty").getValue().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "Nothing to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
//update data
    public void remake(View view) {
DatabaseReference upRef=FirebaseDatabase.getInstance().getReference().child("Delivery");
upRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.hasChild("D101")){
            try{
                edit.setID(t1.getText().toString().trim());
                edit.setDeliverAddress(address.getText().toString().trim());
                edit.setContact(Integer.parseInt(contact.getText().toString().trim()));
                edit.setQty(Integer.parseInt(qty.getText().toString().trim()));

                dbRef=FirebaseDatabase.getInstance().getReference().child("Delivery").child("D101");
                dbRef.setValue(edit);

                ClearControls();
                Toast.makeText(getApplicationContext(), "DataUpdatedSuccess", Toast.LENGTH_SHORT).show();

            }
            catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(), "invalid contact Number", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "invalid quantity", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "update not success", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
    //delete data
public void ignore (View view){
        DatabaseReference delRep=FirebaseDatabase.getInstance().getReference().child("Delivery");
        delRep.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("D101")){
                    dbRef=FirebaseDatabase.getInstance().getReference().child("Delivery").child("D101");
                    dbRef.removeValue();
                    ClearControls();
                    Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No source to Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}

}