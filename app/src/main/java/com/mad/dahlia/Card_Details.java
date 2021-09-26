package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Card_Details extends AppCompatActivity {
    CheckBox card;
    TextView amount;
    EditText cardNo, expire, cvc;
    public static final String Amount = "com.mad.dahlia.MESSAGE";
    public static final String Receiver = "com.mad.dahlia.RECEIVER";
    public static final String type = "com.mad.dahlia.TYPE";
    String getReceiver[];
    DatabaseReference DB;
    Credit_Card creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        card = findViewById(R.id.saveCard);
        amount = findViewById(R.id.RDAmount);
        amount.setText("LKR " + getIntent().getStringExtra(Payment_Type.Amount));
        getReceiver = getIntent().getStringArrayExtra(Payment_Type.Receiver);

        cardNo = findViewById(R.id.CardNo);
        expire = findViewById(R.id.Expire);
        cvc = findViewById(R.id.CVC);

        creditCard = new Credit_Card();
    }

    public void getReview(View view){
        cardNo.getText().toString();
        expire.getText().toString();
        cvc.getText().toString();

        if(card.isChecked()){
            DB = FirebaseDatabase.getInstance().getReference().child("Credit");

            try {
                if (TextUtils.isEmpty(cardNo.toString())) {
                    Toast.makeText(getApplicationContext(), "Fill the card number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(expire.toString())) {
                    Toast.makeText(getApplicationContext(), "Fill Card expire date", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cvc.toString())) {
                    Toast.makeText(getApplicationContext(), "Fill the card CVC number", Toast.LENGTH_SHORT).show();
                } else {
                    creditCard.setCardNo(cardNo.getText().toString());
                    creditCard.setExpire(expire.getText().toString());
                    creditCard.setCvc(Integer.parseInt(cvc.getText().toString()));

                    DB.child("Card").setValue(creditCard);

                    Toast.makeText(getApplicationContext(), "Card Details saved successfully", Toast.LENGTH_LONG).show();

                }
            }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(), "Number format error", Toast.LENGTH_SHORT).show();
            }
        }

        Intent intent = new Intent(this, Order_Review.class);
        intent.putExtra(Amount, getIntent().getStringExtra(Payment_Type.Amount));
        intent.putExtra(Receiver, getReceiver);
        intent.putExtra(type, "Credit Card");
        startActivity(intent);

    }

    public void goBack(View view){
        Intent intent = new Intent(this, Payment_Type.class);
        startActivity(intent);
    }
}