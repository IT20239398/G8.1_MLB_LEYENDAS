package com.mad.dahlia;


import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFragment extends Fragment {


    EditText fullName, email, password, rePassword;
    Button regbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@+[a-z]+\\.+[a-z]+";
    DatabaseReference dbf;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;


   CustInfo cInfo;

    private void clearControlls(){
        fullName.setText("");
        email.setText("");
        password.setText("");
        rePassword.setText("");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        fullName = v.findViewById(R.id.personName);
        email = v.findViewById(R.id.EmailAddress);
        password = v.findViewById(R.id.Password);
        rePassword = v.findViewById(R.id.rePassword);
        regbtn = v.findViewById(R.id.button_reg);
        progressDialog = new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();


        cInfo = new CustInfo();

        regbtn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fName = fullName.getText().toString();
                String EmailAdd = email.getText().toString();
                String pass = password.getText().toString();
                String rePass = rePassword.getText().toString();

                if (!EmailAdd.matches(emailPattern)){
                    email.setError("Enter Valid Email!");
                }
                else if (pass.isEmpty() || pass.length()<6){
                    password.setError("Minimum six characters");
                }
                else if (!pass.equals(rePass)){
                    password.setError("Password not matched");
                }
                else
                    progressDialog.setMessage("Wait while Registration");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(EmailAdd,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity(), "Registering", Toast.LENGTH_SHORT).show();
                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer_Details");
                                cInfo.setFullName(fName);
                                cInfo.setEmail(EmailAdd);
                                cInfo.setPass(pass);
                                cInfo.setAddress("Update Address");
                                cInfo.setGender("Update Gender");
                                cInfo.setbDay("Update Birthday");
                                cInfo.setPhone("Update Contact");
                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                        setValue(cInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                      if (task.isSuccessful()){
                                          Toast.makeText(getActivity(), "Successfully Registered..!", Toast.LENGTH_SHORT).show();
                                      }
                                      else
                                          Toast.makeText(getActivity(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                progressDialog.dismiss();
                                clearControlls();
                                Toast.makeText(getActivity(), "Successfully Registered..!", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Error While Registering" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });

        return v;


    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//
//    }





}