package com.mad.dahlia;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    EditText email,password;
    TextView forgetPassword;
    Button loginButton;
    FirebaseAuth firebaseAuth;
//    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.editTextTextEmailAddress);
        password = view.findViewById(R.id.editTextTextPassword);
        forgetPassword = view.findViewById(R.id.forgetPassword);
        loginButton = view.findViewById(R.id.button_log);
//        progressBar = view.findViewById(R.id.loginProgressBar);
        firebaseAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailLog = email.getText().toString();
                String passLog = password.getText().toString();

                if (TextUtils.isEmpty(email.getText().toString()) || !emailLog.contains("@")){
                    Toast.makeText(getActivity(), "Email invalid..!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(getActivity(), "Password required", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(emailLog,passLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Login in...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getActivity(),Profile.class);
                            startActivity(intent);
                        }else
                            Toast.makeText(getActivity(), "Login Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        return view;

    }
}