package com.mad.dahlia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.UUID;

public class Profile extends AppCompatActivity {


    TextView textEditPro, fullName, email, address, phone, bDay, gender;
    ImageButton cart;
    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private  String userID;
    CustInfo cInfo;
    FirebaseAuth fbA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.profileImage);
        textEditPro = findViewById(R.id.textView13);
        cart = findViewById(R.id.bagBtn);
        fullName = findViewById(R.id.proFullName);
        email = findViewById(R.id.proEmail);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.textViewphone);
        bDay = findViewById(R.id.bday);
        gender = findViewById(R.id.textViewgender);

        fbA = FirebaseAuth.getInstance();
        cInfo = new CustInfo();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

        //Retrieve PROFILE DETAILS from DB
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer_Details").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    fullName.setText(snapshot.child("fullName").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    address.setText(snapshot.child("address").getValue().toString());
                    bDay.setText(snapshot.child("bDay").getValue().toString());
                    phone.setText(snapshot.child("phone").getValue().toString());
                    gender.setText(snapshot.child("gender").getValue().toString());
                    profilePic.setImageURI(Uri.parse("gs://dahlia-fe78e.appspot.com"));


                }else
                    Toast.makeText(getApplication(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textEditPro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Profile.this,EditProfile.class);
                startActivity(intent);



                Toast.makeText( getApplication(), "Edit Profile Details", Toast.LENGTH_SHORT).show();

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,CartView.class);
                startActivity(intent);
            }
        });




    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/");

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                    @Override
                    public  void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded..",
                                Snackbar.LENGTH_LONG ).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();;
                Toast.makeText(getApplication(), "Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        })
          .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
              @Override
              public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                  double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                  pd.setMessage("Percentage: " + (int) progressPercent + "%");
              }
          });
    }
}