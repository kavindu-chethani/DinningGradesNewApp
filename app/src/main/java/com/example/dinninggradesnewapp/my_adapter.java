package com.example.dinninggradesnewapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class my_adapter extends FirebaseRecyclerAdapter<tableReserveModel,my_adapter.myviewholder>{
    public my_adapter(@NonNull FirebaseRecyclerOptions<tableReserveModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull my_adapter.myviewholder holder, @SuppressLint("RecyclerView") int position,
                                    @NonNull tableReserveModel model) {
        holder.table_no.setText(model.getTableno());
        holder.table_floor.setText(model.getFloor());
        holder.no_of_guest.setText(model.getNoofguest());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.time.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                EditText table_no=myview.findViewById(R.id.utableno);
                EditText table_floor=myview.findViewById(R.id.ufloor);
                EditText no_of_guest=myview.findViewById(R.id.unoofguest);
                EditText date=myview.findViewById(R.id.udate);
                EditText time=myview.findViewById(R.id.utime);
                Button submit=myview.findViewById(R.id.usubmit);

                table_no.setText(model.getTableno());
                table_floor.setText(model.getFloor());
                no_of_guest.setText(model.getNoofguest());
                date.setText(model.getDate());
                time.setText(model.getTime());

                dialogPlus.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("table_no",table_no.getText().toString());
                        map.put("table_floor",table_floor.getText().toString());
                        map.put("no_of_guest",no_of_guest.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("tableReserve")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {

                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.date.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("tableReserve")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public my_adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView edit, delete;

        TextView table_no,table_floor,no_of_guest,date,time;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            table_no=(TextView)itemView.findViewById(R.id.table_no);
            table_floor=(TextView)itemView.findViewById(R.id.table_floor);
            no_of_guest=(TextView)itemView.findViewById(R.id.no_of_guest);
            date=(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.time);
            edit=(ImageView) itemView.findViewById(R.id.editicon);
           delete=(ImageView) itemView.findViewById(R.id.deleteicon);



        }
    }




}
