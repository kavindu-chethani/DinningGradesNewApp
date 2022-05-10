import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class paymentCardActivity extends AppCompatActivity {

    RecyclerView recview;
    com.example.projectmad.myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_card);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<cardDetails> options=
                new FirebaseRecyclerOptions.Builder<cardDetails>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cardDetails"), cardDetails.class)
                        .build();

        adapter =  new com.example.projectmad.myadapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }




//    public void onUpdateClick(View view)
//    {
//        startActivity(new Intent(this, editCardActivity.class));
//        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
//    }

}