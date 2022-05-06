package com.example.madfinalproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityViewCardDataBinding;
import com.example.madfinalproject.databinding.ActivityViewCardDataBindingImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.DataFormatException;


public class ViewCardData extends AppCompatActivity {
    TextView textViewPaymentData;
    TextView lbl_total_price;
    //This newDate is created for store Database reference.
    String newDate;

    ActivityViewCardDataBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCardDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textViewPaymentData = findViewById(R.id.textViewCrdNumber);
        Intent intent =  getIntent();
        newDate = intent.getStringExtra("newDate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewCrdData(newDate);
    }

    private void viewCrdData(String newDate){
        reference = FirebaseDatabase.getInstance().getReference("Payment");
        reference.child(newDate).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                   if(task.getResult().exists()){

                       Toast.makeText(ViewCardData.this,"Successfully Get Data From Database",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String crdNumber = String.valueOf(dataSnapshot.child("crdNumber").getValue());
                        String crdHolder =String.valueOf(dataSnapshot.child("crdHolder").getValue());
                        binding.textViewCrdNumber.setText("Card Number : "+crdNumber+"  | Card Holder Name :"+crdHolder);
                   }
                   else {
                       Toast.makeText(ViewCardData.this,"Payment Data does not exist",Toast.LENGTH_SHORT).show();
                   }

                }
                else {
                    Toast.makeText(ViewCardData.this,"Failed to Get Data From Database",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}