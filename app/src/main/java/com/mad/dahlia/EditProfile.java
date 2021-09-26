package com.mad.dahlia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    EditText fullName,email,phonNum,birthDay,address;
    Spinner gen;
    Button save,delete;
    CustInfo cInfo;
    FirebaseAuth fbA;

    private DatabaseReference databaseReference;
    String genSelection;


    private Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    String[] gender = {"Female","Male"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        spinner = findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(EditProfile.this, android.R.layout.simple_spinner_dropdown_item, gender);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    genSelection = gender[i];
                    System.out.println(genSelection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fullName = findViewById(R.id.fNameSpace);
        email = findViewById(R.id.emailSpace);
        phonNum = findViewById(R.id.phoneSpace);
        birthDay = findViewById(R.id.birthSpace);
        address = findViewById(R.id.addressSpace);

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        fbA = FirebaseAuth.getInstance();
        cInfo = new CustInfo();

        //Retrieve from DB
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer_Details").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    fullName.setText(snapshot.child("fullName").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    address.setText(snapshot.child("address").getValue().toString());
                    birthDay.setText(snapshot.child("bDay").getValue().toString());
                    phonNum.setText(snapshot.child("phone").getValue().toString());


                }else
                    Toast.makeText(getApplication(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateDB = FirebaseDatabase.getInstance().getReference().child("Customer_Details");
                cInfo.setFullName(fullName.getText().toString().trim());
                cInfo.setEmail(email.getText().toString().trim());
                cInfo.setPhone(phonNum.getText().toString().trim());
                cInfo.setbDay(birthDay.getText().toString().trim());
                cInfo.setAddress(address.getText().toString().trim());
                cInfo.setGender(genSelection);

                updateDB.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(cInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplication(), "successfully Updated..", Toast.LENGTH_SHORT).show();
                            
                        }else
                            Toast.makeText(getApplication(), "Error...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EditProfile.this,Profile.class);
                        startActivity(intent);
                    }


                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference deleteDB = FirebaseDatabase.getInstance().getReference().child("Customer_Details");
                deleteDB.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer_Details").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.removeValue();
                            Intent intent = new Intent(EditProfile.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplication(), "Account Deleted!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplication(), "Error...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }


}