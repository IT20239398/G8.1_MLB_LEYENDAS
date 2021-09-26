package com.mad.dahlia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Saved_Card_Details extends AppCompatActivity {
    EditText cardno, expire, cvc;
    DatabaseReference DB;
    Credit_Card credit_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_card_details);

        cardno = findViewById(R.id.CardNo);
        expire = findViewById(R.id.Expire);
        cvc = findViewById(R.id.CVC);

        DB = FirebaseDatabase.getInstance().getReference().child("Credit").child("Card");
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    cardno.setText(snapshot.child("cardNo").getValue().toString());
                    expire.setText(snapshot.child("expire").getValue().toString());
                    cvc.setText(snapshot.child("cvc").getValue().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Add card details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateCard(View view){
        DB = FirebaseDatabase.getInstance().getReference().child("Credit");
        credit_card = new Credit_Card();
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Card")){
                    try {
                        credit_card.setCardNo(cardno.getText().toString());
                        credit_card.setExpire(expire.getText().toString());
                        credit_card.setCvc(Integer.parseInt(cvc.getText().toString()));

                        DB = FirebaseDatabase.getInstance().getReference().child("Credit").child("Card");
                        DB.setValue(credit_card);
                        Toast.makeText(getApplicationContext(), "card details successfully updated", Toast.LENGTH_SHORT).show();

                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "Number foarmat error", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Make a payment and save your card details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteCard(View view){
        DB = FirebaseDatabase.getInstance().getReference().child("Credit");
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("Card")){
                    DB.child("Card").removeValue();
                    Toast.makeText(getApplicationContext(), "card details deleted successfully", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "No data to remove", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}