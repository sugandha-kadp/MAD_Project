package com.example.madfinalproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityViewCardDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ViewCardData extends AppCompatActivity {
    TextView textViewPaymentData;
    TextView lbl_total_price;
    TextView textViewPaymentDataCrdHolder;
    TextView textViewPaymentDataCrdNumber;
    TextView textViewPaymentDataCrdType;
    //This newDate is created for store Database reference.
    String newDate;

    Button btnPayNow;
    Button btnRemove;
    AlertDialog.Builder builder;

    String crdNumber;
    String crdHolder;
    String cvv;
    String validUntil;
    String value;
    String crdType;

    ActivityViewCardDataBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCardDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnPayNow = findViewById(R.id.btnRemove);
        btnRemove = findViewById(R.id.btnRemove);
        builder = new AlertDialog.Builder(this);
//        textViewPaymentData = findViewById(R.id.textViewPaymentData);
        textViewPaymentDataCrdHolder =findViewById(R.id.textViewPaymentDataCrdHolder);
        textViewPaymentDataCrdNumber =findViewById(R.id.textViewPaymentDataCrdHolder);
        textViewPaymentDataCrdType =findViewById(R.id.textViewPaymentDataCrdType);


        //Get Reference number From Previous Activity
        Intent intent = getIntent();
        newDate = intent.getStringExtra("newDate");

        viewCrdData(newDate);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openEditPaymentData(v);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Remove")
                        .setMessage("Are you sure you want to remove Payment Data ? ")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                deletePaymentData(newDate);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

            }
        });

        binding.btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentSuccesfull(v);
            }
        });

    }

    private void deletePaymentData(String newDate) {
        reference = FirebaseDatabase.getInstance().getReference("Payment");
        reference.child(newDate).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ViewCardData.this, "Payment Data has been removed", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(ViewCardData.this, "Payment Data could not removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        viewCrdData(newDate);
    }

    private void viewCrdData(String newDate) {
        reference = FirebaseDatabase.getInstance().getReference("Payment");
        reference.child(newDate).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        Toast.makeText(ViewCardData.this, "Successfully Get Data From Database", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();

                        crdNumber = String.valueOf(dataSnapshot.child("crdNumber").getValue());
                        crdHolder = String.valueOf(dataSnapshot.child("crdHolder").getValue());
                        cvv = String.valueOf(dataSnapshot.child("cvv").getValue());
                        validUntil = String.valueOf(dataSnapshot.child("validUntil").getValue());
                        value = String.valueOf(dataSnapshot.child("value").getValue());
                        crdType = String.valueOf(dataSnapshot.child("crdType").getValue());

//                        binding.textViewPaymentData.setText("Card Number : " + crdNumber + "  | Card Holder Name :" + crdHolder);
                        binding.textViewPaymentDataCrdHolder.setText("Card Holder Name : "+crdHolder);
                        binding.textViewPaymentDataCrdNumber.setText("Card Number : "+crdNumber);
                        binding.textViewPaymentDataCrdType.setText("Card Type : "+crdType);
                        binding.lblTotalPrice.setText(value);
                    } else {
                        Toast.makeText(ViewCardData.this, "Payment Data does not exist", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ViewCardData.this, "Failed to Get Data From Database", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void openEditPaymentData(View view) {

        Intent intent = new Intent(this, Edit.class);

        intent.putExtra("newDate", newDate);
        intent.putExtra("crdNumber", crdNumber);
        intent.putExtra("cvv", cvv);
        intent.putExtra("validUntil", validUntil);
        intent.putExtra("crdHolder", crdHolder);
        intent.putExtra("value", value);
        intent.putExtra("crdType",crdType);

        startActivity(intent);

    }

    public void openPaymentSuccesfull(View view) {

        Intent intent = new Intent(this, PaymentSuccesfull.class);

        intent.putExtra("newDate", newDate);
        intent.putExtra("crdNumber", crdNumber);
        intent.putExtra("cvv", cvv);
        intent.putExtra("validUntil", validUntil);
        intent.putExtra("crdHolder", crdHolder);
        intent.putExtra("value", value);
        intent.putExtra("crdType",crdType);



        startActivity(intent);

    }

    //navigation Go Back
    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}