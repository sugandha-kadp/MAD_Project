package com.example.madfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityAddFeedbackBinding;
import com.example.madfinalproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedback extends AppCompatActivity {

    ActivityAddFeedbackBinding binding;
    String feedback;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFeedbackBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_add_feedback);

        binding.addfeedbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = binding.txtfeedback.getText().toString();

                if(!feedback.isEmpty()){

                    AddFeedback addfeedback = new AddFeedback();
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Feedback");

                    reference.child(feedback).setValue(addfeedback).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.txtfeedback.setText("");

                            Toast.makeText(AddFeedback.this, "Your Feedback is Successfully Added ! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    openfeedback(v);

                } else {
                    Toast.makeText(AddFeedback.this, "Fill all required details ! ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
        private void openfeedback(View v) {
            Intent intent = new Intent(this, ViewFeedback.class);
            startActivity(intent);
        }
            public void goBack (View view) {
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);

            }
}