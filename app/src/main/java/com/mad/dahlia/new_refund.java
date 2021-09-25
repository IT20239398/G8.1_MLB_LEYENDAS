package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_refund extends AppCompatActivity {
    Button btnSubmitRef;
    EditText TxtpurchDateadd, txtpurcTypeadd, txtprCodeadd, txtprnameadd, txtReasonadd;
    DatabaseReference ref;
    Refund refund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_refund);

        TxtpurchDateadd = (EditText) findViewById(R.id.TxtpurchDateadd);
        txtpurcTypeadd = (EditText) findViewById(R.id.txtpurcTypeadd);
        txtprCodeadd = (EditText) findViewById(R.id.txtprCodeadd);
        txtprnameadd = (EditText) findViewById(R.id.txtprnameadd);
        txtReasonadd = (EditText) findViewById(R.id.txtReasonadd);
        refund = new Refund();
        ref = FirebaseDatabase.getInstance().getReference().child("Refund");

        btnSubmitRef = findViewById(R.id.btnSubmit);
        btnSubmitRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TxtpurchDateadd.length()==0){
                    Toast.makeText(new_refund.this,
                            "Purchased date field should not be empty",
                            Toast.LENGTH_LONG).show();
                }else if(txtpurcTypeadd.length()==0){
                Toast.makeText(new_refund.this,
                        "Purchased Type field should not be empty",
                        Toast.LENGTH_LONG).show();
                }else if(txtprCodeadd.length()==0){
                    Toast.makeText(new_refund.this,
                            "Product code field should not be empty",
                            Toast.LENGTH_LONG).show();
                }else if(txtprnameadd.length()==0){
                    Toast.makeText(new_refund.this,
                            "Product name field should not be empty",
                            Toast.LENGTH_LONG).show();
                }else if(txtReasonadd.length()==0){
                    Toast.makeText(new_refund.this,
                            "Reason to return field should not be empty",
                            Toast.LENGTH_LONG).show();
                }else {
                    refund.setDate(TxtpurchDateadd.getText().toString().trim());
                    refund.setType(txtpurcTypeadd.getText().toString().trim());
                    refund.setCode(txtprCodeadd.getText().toString().trim());
                    refund.setName(txtprnameadd.getText().toString().trim());
                    refund.setReason(txtReasonadd.getText().toString().trim());
                    ref.push().setValue(refund);
                    Toast.makeText(new_refund.this,
                            "Refund request added",
                            Toast.LENGTH_LONG).show();
                    sendToMyRefunds();
                }

            }
        });

}
        public void sendToMyRefunds(){

            Intent intent1 = new Intent(this, My_refunds.class);
            startActivity(intent1);
        }

}