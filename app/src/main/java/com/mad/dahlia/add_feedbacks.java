package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class add_feedbacks extends AppCompatActivity {




    EditText editTextComment;
    Button SubmitBtn;
    DatabaseReference ref;
    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedbacks);

        SubmitBtn = findViewById(R.id.SubmitBtn);
        editTextComment = findViewById(R.id.editTextComment);
        feedback = new Feedback();
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
    SubmitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            feedback.setComment(editTextComment.getText().toString().trim());
            ref.push().setValue(feedback);
            Toast.makeText(add_feedbacks.this,
                    "Feedback successfully added",
                    Toast.LENGTH_LONG).show();
        }
    });

    }

}
