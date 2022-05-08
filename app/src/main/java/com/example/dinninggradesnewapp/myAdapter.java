package com.example.dinninggradesnewapp;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dinninggradesnewapp.models.tableReserveModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class myAdapter extends FirebaseRecyclerAdapter<tableReserveModel,myAdapter.myviewholder>
{

    public myAdapter(@NonNull FirebaseRecyclerOptions<tableReserveModel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView")
    final int position, @NonNull final tableReserveModel model) {

        holder.tableno.setText(model.getTableno());
        holder.floor.setText(model.getFloor());
        holder.noofguest.setText(model.getNoofguest());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

        //edit function
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.floor.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText tableno=myview.findViewById(R.id.table_no);
                final EditText tablefloor=myview.findViewById(R.id.table_floor);
                final EditText noofguest=myview.findViewById(R.id.no_of_guest);
                final EditText date=myview.findViewById(R.id.date);
                final EditText time=myview.findViewById(R.id.time);
                Button submit=myview.findViewById(R.id.usubmit);

                tableno.setText(model.getTableno());
                tablefloor.setText(model.getFloor());
                noofguest.setText(model.getNoofguest());
                date.setText(model.getDate());
                time.setText(model.getTime());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("tableno",tableno.getText().toString());
                        map.put("tablefloor",tablefloor.getText().toString());
                        map.put("noofguest",noofguest.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
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

//delete function
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.floor.getContext());
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

    // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {

        Button edit,delete;
        TextView tableno,floor,noofguest,date,time;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            tableno=(TextView)itemView.findViewById(R.id.table_no);
            floor=(TextView)itemView.findViewById(R.id.table_floor);
            noofguest=(TextView)itemView.findViewById(R.id.no_of_guest);
            date=(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.time);

            edit=(Button)itemView.findViewById(R.id.edit);
            delete=(Button) itemView.findViewById(R.id.delete);
        }


    }
}
