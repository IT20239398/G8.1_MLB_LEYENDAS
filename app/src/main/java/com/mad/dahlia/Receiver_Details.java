package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Receiver_Details extends AppCompatActivity {
    TextView amount;
    public static final String Amount = "com.mad.dahlia.MESSAGE";
    public static final String Receiver = "com.mad.dahlia.RECEIVER";
    EditText fname, lname, address, email, contactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_details);

        amount = findViewById(R.id.RDAmount);
        amount.setText("LKR "+getIntent().getStringExtra(MainActivity.Amount));

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        contactNo = findViewById(R.id.contactNo);
    }

    public void getPaymentType(View view){
        String[] receiver = {
                fname.getText().toString(),
                lname.getText().toString(),
                address.getText().toString(),
                email.getText().toString(),
                contactNo.getText().toString()
        };

        Intent intent = new Intent(this, Payment_Type.class);
        intent.putExtra(Amount, getIntent().getStringExtra(MainActivity.Amount));
        intent.putExtra(Receiver, receiver);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}