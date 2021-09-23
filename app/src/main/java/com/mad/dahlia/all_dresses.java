package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class all_dresses extends AppCompatActivity {

    ImageButton dressfourteen;
    ImageButton backward;
    ImageButton wlbtn1;
    ImageButton wlbtn2;
    ImageButton wlbtn3;
    ImageButton wlbtn4;

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
                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });

        wlbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(all_dresses.this,
                        "Added to the wish list",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}