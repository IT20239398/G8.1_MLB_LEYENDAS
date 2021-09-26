package com.mad.dahlia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;


public class dress_fourteen extends AppCompatActivity {

    private Spinner spinnerdresssize;
    RadioGroup radioGroup;
    ImageButton backward2;
    TextView qntvalue;
    Button addtocart;
    AlertDialog.Builder builder;

    cartushani cartushani;
    TextView dressname,dressprice,dresscode;
    ImageView dressimage;
    RadioButton red,pink,blue,black;
    String dresscolor;
    DatabaseReference DB;

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
        dressname=(TextView) findViewById(R.id.txt_dress14name);
        dressprice=(TextView)findViewById(R.id.txt_dress14price);
        dresscode=(TextView) findViewById(R.id.txt_dresscode14);
        dressimage=(ImageView)findViewById(R.id.img_dress14);
//
        red = findViewById(R.id.rbcolorred);
        pink = findViewById(R.id.rbcolorpink);
        blue = findViewById(R.id.rbcolorblue);
        black = findViewById(R.id.rbcolorblack);
        cartushani= new cartushani();
//
//
        addtocart=(Button)findViewById(R.id.btn_addtocart);
////        spinnerdresssize=(Spinner)findViewById(R.id.spinnerdresssize);
////        radioGroup=(RadioGroup)findViewById(R.id.rdcolorgroup);
//        dref= FirebaseDatabase.getInstance().getReference().child("cart");
//
    /*    addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dressname.getText().toString();
//                String dressprice = String.parseString(dressprice.getText().toString().trim());
            }
        }); */
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
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dresssizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdresssize.setAdapter(adapter);



        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCart();
                validation();
            }
        });



    }

    public void insertCart(){
        DB = FirebaseDatabase.getInstance().getReference().child("CartDet");

        dressname.getText().toString();
        dresscode.getText().toString();
        dressprice.getText().toString();
        qntvalue.getText().toString();

            if(red.isChecked()){
                dresscolor = "Red";
            }else if (pink.isChecked()){
                dresscolor = "Pink";
            }else if (blue.isChecked()){
                dresscolor = "Blue";
            }
            else {
                dresscolor = "Black";
            }

        try {
            if (TextUtils.isEmpty(dressname.toString())){
                Toast.makeText(getApplicationContext(), "Dress name is empty", Toast.LENGTH_SHORT).show();
            }else {
                cartushani.setDressname(dressname.toString());
                cartushani.setDresscolor(dresscolor);
                cartushani.setDressprice(dressprice.toString());
                cartushani.setDresscode(dresscode.toString());
                cartushani.setQuantity(Integer.parseInt(qntvalue.toString()));

                DB.child("test").setValue(cartushani);
                Toast.makeText(getApplicationContext(), "Data Saved successfully", Toast.LENGTH_LONG).show();
            }
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Number format error", Toast.LENGTH_SHORT).show();
        }
    }

    //validation of the radio group

    public void validation(){

        int isSelected = radioGroup.getCheckedRadioButtonId();

        if(isSelected == -1){
            Toast.makeText(dress_fourteen.this,"you have not selected a color",Toast.LENGTH_LONG).show();
            return;
        }


        builder.setTitle("")
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