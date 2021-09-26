package com.mad.dahlia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Order_Review extends AppCompatActivity {
    String getReceiver[], amount;
    TextView Dcharge, Damount, Samount, Tamount, Ptype;
    double charge, damount, samount, tamount, discount = 0;

    TextView name, address, email, contactNo;
    String type;

    TextView drsName, drsColor, drsSize, drsPrice, drsQty;

    DatabaseReference DB;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);

        amount = getIntent().getStringExtra(Payment_Type.Amount);
        getReceiver = getIntent().getStringArrayExtra(Payment_Type.Receiver);
        type = getIntent().getStringExtra(Payment_Type.type).toString();
        if(amount == null){
            amount = getIntent().getStringExtra(Card_Details.Amount);
            getReceiver = getIntent().getStringArrayExtra(Card_Details.Amount);
            type = getIntent().getStringExtra(Card_Details.type).toString();
        }

        Dcharge = findViewById(R.id.Dcharge);
        Damount = findViewById(R.id.Damount);
        Samount = findViewById(R.id.Samount);
        Tamount = findViewById(R.id.Tamount);

        name = findViewById(R.id.ORRname);
        address = findViewById(R.id.ORRaddress);
        email = findViewById(R.id.ORRemail);
        contactNo = findViewById(R.id.ORRcontact);
        Ptype = findViewById(R.id.ORRpaytype);

        drsName = findViewById(R.id.drsname);
        drsColor = findViewById(R.id.drscolor);
        drsSize = findViewById(R.id.drssize);
        drsPrice = findViewById(R.id.drsprice);
        drsQty = findViewById(R.id.drsqty);

        Receiver();
        Calculate();
        getItems();
    }

    private void getItems() {
        DB = FirebaseDatabase.getInstance().getReference().child("Cart");
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    drsName.setText(snapshot.child("Itemname").getValue().toString());
                    drsColor.setText(snapshot.child("Color").getValue().toString());
                    drsSize.setText(snapshot.child("Size").getValue().toString());
                    drsPrice.setText(snapshot.child("Price").getValue().toString());
                    drsQty.setText(snapshot.child("Quantity").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void Calculate(){
        samount = Double.parseDouble(amount.toString());

        charge = samount*0.05;
        damount = samount*discount;
        tamount = samount + charge - damount;

        Samount.setText("LKR " + samount);
        Dcharge.setText("LKR " + String.valueOf(charge));
        Damount.setText("LKR " + String.valueOf(damount));
        Tamount.setText("LKR " + String.valueOf(tamount));

    }

    protected  void Receiver(){
        name.setText(getReceiver[0].toString() + " " + getReceiver[1].toString());
        address.setText(getReceiver[2].toString());
        email.setText(getReceiver[3].toString());
        contactNo.setText(getReceiver[4].toString());
        Ptype.setText(type);
    }

    public void GoBack(View view){
        Intent intent = new Intent(this, Payment_Type.class);
        startActivity(intent);
    }

    public void addOrder(View view){
        DB = FirebaseDatabase.getInstance().getReference().child("Orders").child("Username");
        order = new Order();

        try {
            if(TextUtils.isEmpty(Ptype.toString())){
                Toast.makeText(getApplicationContext(), "select payment type", Toast.LENGTH_SHORT).show();
            }else {
                order.setName(name.getText().toString());
                order.setAddress(address.getText().toString());
                order.setEmail(email.getText().toString());
                order.setContactNo(contactNo.getText().toString());
                order.setType(Ptype.getText().toString());

                order.setCharge(charge);
                order.setDiscount(damount);
                order.setSubtotal(samount);
                order.setTotalAmount(tamount);

                order.setCode(drsName.getText().toString());
                order.setColor(drsColor.getText().toString());
                order.setPrice(drsPrice.getText().toString());
                order.setQuantity(drsQty.getText().toString());

                DB.child("set").setValue(order);

                Toast.makeText(getApplicationContext(), "Order successfully added", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Number format error", Toast.LENGTH_SHORT).show();
        }
    }
}