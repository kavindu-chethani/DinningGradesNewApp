package com.example.dinninggradesnewapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter <cardDetails,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<cardDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder,final int position, @NonNull final cardDetails model) {
        holder.name.setText(model.getName());
        holder.cardNumber.setText(model.getCardNumber());
        holder.expDate.setText(model.getExpDate());
        holder.cvv.setText(model.getCvv());
        holder.bank.setText(model.getBank());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        Button edit, delete;
        TextView name, cardNumber, expDate, cvv, bank;

        public myviewholder(@NonNull View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nametext);
            cardNumber = (TextView) itemView.findViewById(R.id.cardNumbertext);
            expDate = (TextView) itemView.findViewById(R.id.expDatetext);
            cvv = (TextView) itemView.findViewById(R.id.cvvtext);
            bank = (TextView) itemView.findViewById(R.id.banktext);

            edit = (Button) itemView.findViewById(R.id.cirUpdateButton);
            delete = (Button) itemView.findViewById(R.id.cirDeleteButton);

        }
    }
}
