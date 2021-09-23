package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class dress_fourteen extends AppCompatActivity {

    ImageButton backward2;
    TextView qntvalue;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_fourteen);
        backward2 = (ImageButton) findViewById(R.id.btn_backwardtoall) ;

        backward2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backward = new Intent(dress_fourteen.this, all_dresses.class);
                startActivity(backward);
            }
        });

        qntvalue = (TextView) findViewById(R.id.qntvalue);
    }

    public void increment(View v){

        count++;
        qntvalue.setText(" " + count);
    }

    public void decrement(View v){
        if(count <= 1) count = 0;
        else count--;

        qntvalue.setText(" " + count);
    }


}