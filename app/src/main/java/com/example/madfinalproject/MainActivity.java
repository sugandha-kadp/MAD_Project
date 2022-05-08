package com.example.madfinalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;

    ActivityMainBinding binding;
    String crdNumber,validUntil,cvv,crdHolder,value,crdType;
    FirebaseDatabase db;
    DatabaseReference reference;

    String newDate; //This newDate is created for store Database reference.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //crd Type selection Radio Group
        radioGroup =findViewById(R.id.radioGroupPType);

        //This value must be received from Cart within Intent. In this time Cart is not connected, so that assign sample value.
        binding.lblTotalPrice.setText("$1,100.00");

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //get System date with time to reference.
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               Date date = new Date();
               newDate = formatter.format(date);

               //get Radio Button Data
               int radioID = radioGroup.getCheckedRadioButtonId();
               radioButton =findViewById(radioID);

                String crdPID = (String)radioButton.getText().toString();
                 crdType =crdPID;

               crdNumber = binding.editTextCrdNumber.getText().toString();
              validUntil =binding.editTextValidUntil.getText().toString();
              cvv = binding.editTextCVV.getText().toString();
              crdHolder = binding.editTextCardHolder.getText().toString();
              value =binding.lblTotalPrice.getText().toString();

               if(crdNumber.isEmpty())
               {
                   Toast.makeText(MainActivity.this, "Please Enter Card Number ! " , Toast.LENGTH_SHORT).show();
               }
               else if(validUntil.isEmpty()){

                   Toast.makeText(MainActivity.this, "Please Enter Valid Until field ! ", Toast.LENGTH_SHORT).show();
               }
               else if(cvv.isEmpty()) {

                   Toast.makeText(MainActivity.this, "Please Enter CVV number ! ", Toast.LENGTH_SHORT).show();
               }
               else if(crdHolder.isEmpty()){

                   Toast.makeText(MainActivity.this, "Please Enter Card Holder Name ! ", Toast.LENGTH_SHORT).show();
               }


              if(!crdNumber.isEmpty() && !validUntil.isEmpty() && !cvv.isEmpty() && !crdHolder.isEmpty()){
                  Payment payment = new Payment(crdNumber, validUntil, cvv, crdHolder, value, crdType);
                      db = FirebaseDatabase.getInstance();
                      reference = db.getReference("Payment");
                      reference.child(newDate).setValue(payment).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              binding.editTextCrdNumber.setText("");
                              binding.editTextValidUntil.setText("");
                              binding.editTextCVV.setText("");
                              binding.editTextCardHolder.setText("");

                              Toast.makeText(MainActivity.this, "Payment Data Successfully added ! " + newDate + "", Toast.LENGTH_SHORT).show();
                          }
                      });
                      openViewCardData(v);

              }
           }
       });


    }



    //navigation
    public void openViewCardData(View view){
        Intent intent = new Intent(this,ViewCardData.class);

        //send Reference number to next Activity
        intent.putExtra("newDate",newDate);

        startActivity(intent);
    }

    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}