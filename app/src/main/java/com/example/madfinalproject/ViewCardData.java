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
        binding.btnGetData.setOnClickListener( new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!newDate.isEmpty()){
                    viewCrdData(newDate);
                }
                else {
                    Toast.makeText(ViewCardData.this,"newDate notset",Toast.LENGTH_SHORT).show();
                }

            }
        });


        textViewPaymentData = findViewById(R.id.textViewCrdNumber);
        Intent intent =  getIntent();
        newDate = intent.getStringExtra("newDate");


    }

    private void viewCrdData(String newDate){
        reference = FirebaseDatabase.getInstance().getReference("Payment");
        reference.child(newDate).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                   if(task.getResult().exists()){

                       Toast.makeText(ViewCardData.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String crdNumber = String.valueOf(dataSnapshot.child("crdNumber").getValue());
                        binding.textViewCrdNumber.setText(crdNumber);
                   }
                   else {
                       Toast.makeText(ViewCardData.this,"Payment does not exist",Toast.LENGTH_SHORT).show();
                   }

                }
                else {
                    Toast.makeText(ViewCardData.this,"Failed to read",Toast.LENGTH_SHORT).show();
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