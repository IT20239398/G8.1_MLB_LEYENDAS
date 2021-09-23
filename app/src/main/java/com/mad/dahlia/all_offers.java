package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class all_offers extends AppCompatActivity {
    ImageButton backwordtocat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offers);

        backwordtocat = (ImageButton) findViewById(R.id.btn_ofrtocat) ;

        backwordtocat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backwardtocat = new Intent(all_offers.this, Categories.class);
                startActivity(backwardtocat);
            }
        });

    }
}