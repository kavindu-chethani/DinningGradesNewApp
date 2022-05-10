import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class confirmPaymentActivity extends AppCompatActivity {

    EditText editName, editCardNo, editExpDate, editCVV,editBank;
    Button saveCard;

    //add crud operation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);

        editName = findViewById(R.id.editTextOwnerName);
        editCardNo = findViewById(R.id.editTextCardNumber);
        editExpDate = findViewById(R.id.editTextExpireDate);
        editCVV = findViewById(R.id.editTextCVV);
        editBank = findViewById(R.id.editTextBank);

        saveCard = findViewById(R.id.cirSaveCardButton);
        saveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processInsert();
            }
        });
    }

    private void processInsert(){
        Map<String ,Object> map = new HashMap<>();
        map.put("name",editName.getText().toString().trim());
        map.put("cardNo",editCardNo.getText().toString().trim());
        map.put("expDate",editExpDate.getText().toString().trim());
        map.put("cvv",editCVV.getText().toString().trim());
        map.put("bank",editBank.getText().toString().trim());

        FirebaseDatabase.getInstance().getReference().child("cardDetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        editName.setText("");
                        editCardNo.setText("");
                        editExpDate.setText("");
                        editCVV.setText("");
                        editBank.setText("");
                        Toast.makeText(getApplicationContext(), "Card details Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to enter card details", Toast.LENGTH_SHORT).show();

                    }
                });


    }





   /* public void onPayNowClick(View view)
    {
        startActivity(new Intent(this,#.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }*/

    public void onCardDetailsClick(View view)
    {
        startActivity(new Intent(this,paymentCardActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}