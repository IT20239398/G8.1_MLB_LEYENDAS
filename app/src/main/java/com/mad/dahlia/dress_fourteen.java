package com.mad.dahlia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;


public class dress_fourteen extends AppCompatActivity {

    private Spinner spinnerdresssize;
    RadioGroup radioGroup;
    ImageButton backward2;
    TextView qntvalue;
    Button addtocart;
    AlertDialog.Builder builder;

//    Member cartushani;
//    TextView dressname,dressprice,dresscode;
//    ImageView dressimage;
//    RadioButton red,pink,blue,black;
//    DatabaseReference dref;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_fourteen);
        backward2 = (ImageButton) findViewById(R.id.btn_backwardtoall) ;
        addtocart = findViewById(R.id.btn_addtocart);
        builder = new AlertDialog.Builder(this);
        spinnerdresssize = findViewById(R.id.spinnerdresssize);
        radioGroup = (RadioGroup) findViewById(R.id.rdcolorgroup);
        qntvalue = (TextView) findViewById(R.id.qntvalue);

//----crud - insert ------
//        dressname=(TextView) findViewById(R.id.txt_dress14name);
//        dressprice=(TextView)findViewById(R.id.txt_dress14price);
//        dresscode=(TextView) findViewById(R.id.txt_dresscode14);
//        dressimage=(ImageView)findViewById(R.id.img_dress14);
//
//        red = findViewById(R.id.rbcolorred);
//        pink = findViewById(R.id.rbcolorpink);
//        blue = findViewById(R.id.rbcolorblue);
//        black = findViewById(R.id.rbcolorblack);
//        cartushani= new Member();
//
//
////        addtocart=(Button)findViewById(R.id.btn_addtocart);
////        spinnerdresssize=(Spinner)findViewById(R.id.spinnerdresssize);
////        radioGroup=(RadioGroup)findViewById(R.id.rdcolorgroup);
//        dref= FirebaseDatabase.getInstance().getReference().child("cart");
//
//        addtocart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String dressname = String.parseString(dressname.getText().toString().trim());
//                String dressprice = String.parseString(dressprice.getText().toString().trim());
//            }
//        });
//




        backward2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backward = new Intent(dress_fourteen.this, all_dresses.class);
                startActivity(backward);
            }
        });

        //dropdown for the dress size
        String[] dresssizes = getResources().getStringArray(R.array.dress_size);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, dresssizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdresssize.setAdapter(adapter);



        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });



    }

    //validation of the radio group

    public void validation(){

        int isSelected = radioGroup.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(dress_fourteen.this,"you have not selected a color",Toast.LENGTH_LONG).show();
            return;
        }

        //successful alert for the add to cart button
        builder.setTitle("")
                .setMessage("Successfully added to your cart")
                .setCancelable(true)
                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

//        Toast.makeText(dress_fourteen.this,"successfully added to the cart", Toast.LENGTH_LONG).show();

    }

    //increment and decrement of the quantity
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