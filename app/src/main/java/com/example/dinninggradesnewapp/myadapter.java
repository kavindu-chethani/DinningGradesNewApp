package com.example.projectmad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class myadapter extends FirebaseRecyclerAdapter <cardDetails,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<cardDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position , @NonNull final cardDetails model) {
        holder.name.setText( model.getName());
        holder.cardNo.setText("Card No : "+model.getCardNo());
        holder.expDate.setText("Expire date : "+model.getExpDate());
        holder.cvv.setText("cvv : "+model.getCvv());
        holder.bank.setText("Bank : "+model.getBank());

        //edit function
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.cvv.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_edit_card))
                        .setExpanded(true,1100)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText name = myview.findViewById(R.id.editTextOwnerName);
                final EditText cardNo = myview.findViewById(R.id.editTextCardNumber);
                final EditText expDate = myview.findViewById(R.id.editTextExpireDate);
                final EditText cvv = myview.findViewById(R.id.editTextCVV);
                final EditText bank = myview.findViewById(R.id.editTextBank);
                Button submit = myview.findViewById(R.id.updateCardButton);

                name.setText(model.getName());
                cardNo.setText(model.getCardNo());
                expDate.setText(model.getExpDate());
                cvv.setText(model.getCvv());
                bank.setText(model.getBank());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("cardNo",cardNo.getText().toString());
                        map.put("expDate",expDate.getText().toString());
                        map.put("cvv",cvv.getText().toString());
                        map.put("bank",bank.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("cardDetails")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(myview.getContext(), "Card Updated successfully", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cvv.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure you want to delete this details? This cannot be undone");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("cardDetails")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(view.getContext(), "Card deleted successfully", Toast.LENGTH_SHORT).show();
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
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        Button edit, delete;
        TextView name, cardNo, expDate, cvv, bank;

        public myviewholder(@NonNull View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nametext);
            cardNo = (TextView) itemView.findViewById(R.id.cardNumbertext);
            expDate = (TextView) itemView.findViewById(R.id.expDatetext);
            cvv = (TextView) itemView.findViewById(R.id.cvvtext);
            bank = (TextView) itemView.findViewById(R.id.banktext);

            edit = (Button) itemView.findViewById(R.id.cirUpdateButton);
            delete = (Button) itemView.findViewById(R.id.cirDeleteButton);

        }
    }
}