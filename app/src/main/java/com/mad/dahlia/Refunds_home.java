package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Refunds_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refunds_home);

    }
    /** Called when the user taps the NEW REFUND button */
    public void sendToNewRefund(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, new_refund.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user taps the MY COMMENTS button */
    public void sendToMyRefunds(View view) {
        // Do something in response to button
        Intent intent1 = new Intent(this, My_refunds.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent1);
    }
}