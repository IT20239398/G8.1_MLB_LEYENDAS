package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    Button buttonhome;
    Button buttoncategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonhome = (Button) findViewById(R.id.btn_menuhome);
        buttoncategories = (Button) findViewById(R.id.btn_menucategories);

        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(menu.this,Home_activity.class);
                startActivity(home);
            }});

        buttoncategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(menu.this,Categories.class);
                startActivity(home);
            }});
    }
}