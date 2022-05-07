package com.example.madfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityEditBinding;
import com.example.madfinalproject.databinding.ActivityViewCardDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Edit extends AppCompatActivity {

    EditText editTextUpdateCrdNumber;
    EditText editTextUpdateValidUntil;
    EditText editTextUpdateCVV;
    EditText editTextUpdateCardHolder;

    //This newDate is created for store Database reference.
    String newDate;
    String crdNumber;
    String crdHolder;
    String cvv;
    String validUntil;

    ActivityEditBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextUpdateCrdNumber = findViewById(R.id.editTextUpdateCrdNumber);
        editTextUpdateValidUntil = findViewById(R.id.editTextUpdateValidUntil);
        editTextUpdateCVV= findViewById(R.id.editTextUpdateCVV);
        editTextUpdateCardHolder= findViewById(R.id.editTextUpdateCardHolder);

        Intent intent =  getIntent();
        newDate = intent.getStringExtra("newDate");
        crdNumber = intent.getStringExtra("crdNumber");
        cvv = intent.getStringExtra("cvv");
        validUntil = intent.getStringExtra("validUntil");
        crdHolder = intent.getStringExtra("crdHolder");

        viewCrdData();
    }
    @Override
    protected void onStart() {
        super.onStart();
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackPaymentData(v);

                String crdNumberNew = binding.editTextUpdateCrdNumber.getText().toString();
                String validUntilNew =binding.editTextUpdateValidUntil.getText().toString();
                String cvvNew = binding.editTextUpdateCVV.getText().toString();
                String crdHolderNew = binding.editTextUpdateCardHolder.getText().toString();

                editPaymentData(crdNumberNew,crdHolderNew,validUntilNew,cvvNew);

            }
        });

    }

    private void viewCrdData(){
        binding.editTextUpdateCrdNumber.setText(crdNumber);
        binding.editTextUpdateCVV.setText(cvv);
        binding.editTextUpdateValidUntil.setText(validUntil);
        binding.editTextUpdateCardHolder.setText(crdHolder);
    }

    private void editPaymentData(String crdNumber, String crdHolder, String validUntil, String cvv) {

        HashMap Payment = new HashMap();
        Payment.put("crdNumber",crdNumber);
        Payment.put("crdHolder",crdHolder);
        Payment.put("validUntil",validUntil);
        Payment.put("cvv",cvv);

        reference = FirebaseDatabase.getInstance().getReference("Payment");
        reference.child(newDate).updateChildren(Payment).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    binding.editTextUpdateCrdNumber.setText("");
                    binding.editTextUpdateCVV.setText("");
                    binding.editTextUpdateValidUntil.setText("");
                    binding.editTextUpdateCardHolder.setText("");

                    Toast.makeText(Edit.this,"Payment Data Successfully Edited",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Edit.this,"Failed to Edit Payment Data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //navigation Go Back to View Payment Data
    public void goBackPaymentData (View view){
        Intent intent = new Intent(this,ViewCardData.class);
        intent.putExtra("newDate", newDate);
        startActivity(intent);
    }
}