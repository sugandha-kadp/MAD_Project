package com.example.madfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madfinalproject.databinding.ActivityEditBinding;
import com.example.madfinalproject.databinding.ActivityPaymentSuccesfullBinding;
import com.google.firebase.database.DatabaseReference;

public class PaymentSuccesfull extends AppCompatActivity {

    TextView textViewLblTotalPrice;
    TextView lblCrdHolder;
    TextView lblCrdNumber;
    TextView lblDateTime;
    Button btnDone;

    String newDate;
    String crdNumber;
    String crdHolder;
    String cvv;
    String validUntil;
    String value;

    ActivityPaymentSuccesfullBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentSuccesfullBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textViewLblTotalPrice = findViewById(R.id.textViewLblTotalPrice);
        lblCrdHolder = findViewById(R.id.lblCrdHolder);
        lblCrdNumber = findViewById(R.id.lblCrdNumber);
        lblDateTime = findViewById(R.id.lblDateTime);
        btnDone = findViewById(R.id.btnDone);


// Get Data Received from Intent
        Intent intent =  getIntent();
        newDate = intent.getStringExtra("newDate");
        crdNumber = intent.getStringExtra("crdNumber");
        cvv = intent.getStringExtra("cvv");
        validUntil = intent.getStringExtra("validUntil");
        crdHolder = intent.getStringExtra("crdHolder");
        value = intent.getStringExtra("value");

        binding.textViewLblTotalPrice.setText(value);
        binding.lblCrdHolder.setText("Card Holder Name : "+crdHolder);
        binding.lblCrdNumber.setText("Card Number : "+crdNumber);
        binding.lblDateTime.setText("Payment Date And Time : "+newDate);

        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccesfull.this,Shop.class);
                startActivity(intent);
            }
        });
    }
}