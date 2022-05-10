package com.example.projectmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Pattern;

public class sign_upActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFullName, editTextEmail, editTextContact,editTextPW;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.cirRegisterButton);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextContact = (EditText) findViewById(R.id.editTextMobile);
        editTextPW = (EditText) findViewById(R.id.editTextPassword);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cirRegisterButton:
                registerUser();
//                //public void onRegisterClick(View view)
//            //{
//                startActivity(new Intent(this,paymentActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
//           // }

                break;
        }

    }

    private void registerUser() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String password = editTextPW.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email address!");
            editTextEmail.requestFocus();
            return;
        }

        if(contact.isEmpty()){
            editTextContact.setError("Contact Number is required!");
            editTextContact.requestFocus();
            return;
        }

        if(contact.length() < 10){
            editTextContact.setError("Please enter valid Contact Number!");
            editTextContact.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPW.setError("Password is required!");
            editTextPW.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPW.setError("Password must have at least 6 characters!");
            editTextPW.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user user = new user(fullName, email,contact,password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(sign_upActivity.this, "User has been registered successfully!",Toast.LENGTH_LONG).show();
                                        //redirect to login layout

                                    }else{
                                        Toast.makeText(sign_upActivity.this, "Failed to register! Try again!",Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }else {
                            Toast.makeText(sign_upActivity.this, "User register unsuccessful",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, com.example.projectmad.loginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }



}