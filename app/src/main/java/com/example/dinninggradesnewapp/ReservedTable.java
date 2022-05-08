package com.example.dinninggradesnewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinninggradesnewapp.models.tableReserveModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ReservedTable extends AppCompatActivity {
    RecyclerView recview;
    myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_table);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<tableReserveModel> options = new FirebaseRecyclerOptions.Builder<tableReserveModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tableReserve"), tableReserveModel.class)
                        .build();


        recview.setAdapter(adapter);



    }




}