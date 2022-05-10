package com.example.madfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityProfileBinding;
import com.example.madfinalproject.databinding.ActivityViewFeedbackBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFeedback extends AppCompatActivity {

    String Feedback;
    AlertDialog.Builder builder;

    TextView textfeedback;

    ActivityViewFeedbackBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        binding = ActivityViewFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        builder = new AlertDialog.Builder(this);

        textfeedback = findViewById(R.id.textfeedback);

        Intent intent = getIntent();
        Feedback = intent.getStringExtra("Feedback");

        viewFeedbackData(Feedback);

    }

        private void viewFeedbackData(String Feedback) {
            reference = FirebaseDatabase.getInstance().getReference("Feedback");
            reference.child(Feedback).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    if (task.isSuccessful()) {

                        if (task.getResult().exists()) {

                            Toast.makeText(ViewFeedback.this, "Successfully Get Data From Database", Toast.LENGTH_SHORT).show();
                            DataSnapshot dataSnapshot = task.getResult();

                            String Feedback = String.valueOf(dataSnapshot.child("Feedback").getValue());

                            binding.textfeedback.setText("Customer Feedback : " + Feedback);


                        } else {
                            Toast.makeText(ViewFeedback.this, "Feedback Data does not exist", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ViewFeedback.this, "Failed to Get Data From Database", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        public void goBack() {
            Intent intent = new Intent(this, Feedback.class);
            startActivity(intent);

    }
}