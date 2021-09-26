package com.mad.dahlia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class add_feedbacks extends AppCompatActivity {

    EditText editTextComment;
    Button SubmitBtn;
    DatabaseReference ref;
    Feedback feedback;

    RatingBar ratingBarFeedback;
    Button btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedbacks);

        SubmitBtn = findViewById(R.id.SubmitBtn);
        editTextComment = findViewById(R.id.editTextComment);
        feedback = new Feedback();
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");

        ratingBarFeedback = findViewById(R.id.ratingBarFeedback);
        btnRate = findViewById(R.id.btnRate);

    btnRate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String s = String.valueOf(ratingBarFeedback.getRating());
            if (ratingBarFeedback.getRating()==0.0){
                Toast.makeText(add_feedbacks.this,
                        "Rate before submitting",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),s+"Star"
                        ,Toast.LENGTH_LONG).show();
            }

        }
    });


    SubmitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (editTextComment.length() ==0){
                Toast.makeText(add_feedbacks.this,
                        "Field should not be empty",
                        Toast.LENGTH_LONG).show();}
            else{

            feedback.setComment(editTextComment.getText().toString().trim());
            ref.push().setValue(feedback);
            Toast.makeText(add_feedbacks.this,
                    "Feedback successfully added",
                    Toast.LENGTH_LONG).show();
                        sendToMyComments();}
        }

    });

    }
    public void sendToMyComments() {

        Intent intent1 = new Intent(this, my_comments.class);
        startActivity(intent1);


    }


}
