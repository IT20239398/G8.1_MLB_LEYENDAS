package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText amt ;
    public static final String Amount = "com.mad.dahlia.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.amount);
    }

    public void Delivery(View view){
        Intent intent = new Intent(this, Receiver_Details.class);
        intent.putExtra(Amount, amt.getText().toString());
        startActivity(intent);

    }

    public void checkCardDetails(View view){
        Intent intent = new Intent(this, Saved_Card_Details.class);
        startActivity(intent);
    }
}