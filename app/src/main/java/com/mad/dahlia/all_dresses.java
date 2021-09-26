package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class all_dresses extends AppCompatActivity {

    ImageButton dressfourteen;
    ImageButton backward;
    ImageButton wlbtn1;
    ImageButton wlbtn2;
    ImageButton wlbtn3;
    ImageButton wlbtn4;

    DatabaseReference DB;
    TextView name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dresses);

         dressfourteen = (ImageButton) findViewById(R.id.btn_imgdress14);
         backward = (ImageButton) findViewById(R.id.btn_backwardtocat) ;
         wlbtn1 = (ImageButton) findViewById(R.id.btn_drsaddwlbtn1);
         wlbtn2 = (ImageButton) findViewById(R.id.btn_drsaddwlbtn2);
         wlbtn3 = (ImageButton) findViewById(R.id.btn_drsaddwlbtn3);
         wlbtn4 = (ImageButton) findViewById(R.id.btn_drsaddwlbtn4);

        dressfourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(all_dresses.this, dress_fourteen.class);
                startActivity(intent);
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backward = new Intent(all_dresses.this, Categories.class);
                startActivity(backward);
            }
        });

        wlbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.txt_dress13);
                price = findViewById(R.id.txt_dress13p);

                addWishlist();

                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.txt_dress14);
                price = findViewById(R.id.txt_dress14p);
                addWishlist();
                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.txt_dress10);
                price = findViewById(R.id.txt_dress10p);
                addWishlist();

                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.txt_dress11);
                price = findViewById(R.id.txt_dress11p);
                addWishlist();

                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addWishlist(){
        DB = FirebaseDatabase.getInstance().getReference("WishList").child("UserName");

        wishlistModel wish = new wishlistModel();
        String Name = name.getText().toString();

        try {
            wish.setName(name.getText().toString());
            wish.setPrice(price.getText().toString());

            DB.child(Name).setValue(wish);
            Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
            
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Format error", Toast.LENGTH_SHORT).show();
        }
    }
}