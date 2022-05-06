package com.example.madfinalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.madfinalproject.databinding.ActivityViewCardDataBinding;
import com.example.madfinalproject.databinding.ActivityViewCardDataBindingImpl;
import com.google.firebase.database.DatabaseReference;

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
//                String crdNumber = binding.
            }
        });


        textViewPaymentData = findViewById(R.id.textViewCrdNumber);
        Intent intent =  getIntent();
        newDate = intent.getStringExtra("newDate");

        textViewPaymentData.setText(newDate);

    }


    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}