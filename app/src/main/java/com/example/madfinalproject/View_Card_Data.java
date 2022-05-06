package com.example.madfinalproject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;


public class View_Card_Data extends AppCompatActivity {
    TextView textViewPaymentData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_data);

        textViewPaymentData = findViewById(R.id.editTextCrdNumber);

        }


    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}