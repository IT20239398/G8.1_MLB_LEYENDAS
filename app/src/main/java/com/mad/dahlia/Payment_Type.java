package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Payment_Type extends AppCompatActivity {
    RadioButton cash, card;
    TextView amount;
    public static final String Amount = "com.mad.dahlia.MESSAGE";
    public static final String Receiver = "com.mad.dahlia.RECEIVER";
    public static final String type = "com.mad.dahlia.TYPE";
    String [] getReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_type);

        cash = findViewById(R.id.cash);
        card = findViewById(R.id.card);

        amount = findViewById(R.id.RDAmount);
        amount.setText( "LKR "+ getIntent().getStringExtra(Receiver_Details.Amount));
        getReceiver = getIntent().getStringArrayExtra(Receiver_Details.Receiver);
    }


    public void checkAndGo(View view){
        if(cash.isChecked()){
            Intent intent = new Intent(this, Order_Review.class);
            intent.putExtra(Amount, getIntent().getStringExtra(Receiver_Details.Amount));
            intent.putExtra(Receiver, getReceiver);
            intent.putExtra(type, "Cash");
            startActivity(intent);
        }
        else if(card.isChecked()){
            Intent intent = new Intent(this, Card_Details.class);
            intent.putExtra(Amount, getIntent().getStringExtra(Receiver_Details.Amount));
            intent.putExtra(Receiver, getReceiver);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText( getApplicationContext(), "You must select Payment Method..", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(this, Receiver_Details.class);
        startActivity(intent);
    }
}