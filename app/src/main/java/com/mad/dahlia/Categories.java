package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categories extends AppCompatActivity {

    public Button buttondresses;
    public Button buttondoffer;
//    public ImageButton menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
//        menu = (ImageButton) findViewById(R.id.btn_menu) ;
        buttondresses = (Button) findViewById(R.id.btn_catdresses);
        buttondoffer = (Button) findViewById(R.id.btn_catoffers);

        buttondresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dresses = new Intent(Categories.this, all_dresses.class);
                startActivity(dresses);
            }
        });

        buttondoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dresses = new Intent(Categories.this, all_offers.class);
                startActivity(dresses);
            }
        });

    }
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent menu = new Intent(Categories.this, menu.class);
//                startActivity(menu);
//            }
//        });

        public void gotomenu(View view) {
            // Do something in response to button
            Intent intent = new Intent(this, menu.class);
            //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
            //String message = editText.getText().toString();
            // intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    }