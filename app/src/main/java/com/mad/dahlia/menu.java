package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

//    Button buttonhome;
//    Button buttoncategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);}

//        buttonhome = (Button) findViewById(R.id.btn_menuhome);
//        buttoncategories = (Button) findViewById(R.id.btn_menucategories);
//
//        buttonhome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent home = new Intent(menu.this,Home_activity.class);
//                startActivity(home);
//            }});
//
//        buttoncategories.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent home = new Intent(menu.this,Categories.class);
//                startActivity(home);
//            }});

        public void gotohome(View view) {
            // Do something in response to button
            Intent intent = new Intent(this, Home_activity.class);
            //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
            //String message = editText.getText().toString();
            // intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    public void gotocategories(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Categories.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void gotowishlist(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, wish_list.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    }
//}