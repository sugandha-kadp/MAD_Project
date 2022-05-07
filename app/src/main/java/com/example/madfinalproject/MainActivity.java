package com.example.madfinalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madfinalproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String crdNumber,validUntil,cvv,crdHolder;
    FirebaseDatabase db;
    DatabaseReference reference;

    String newDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //get System date with time to refrence.
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
               Date date = new Date();
               newDate = formatter.format(date);

              crdNumber = binding.editTextCrdNumber.getText().toString();
              validUntil =binding.editTextValidUntil.getText().toString();
              cvv = binding.editTextCVV.getText().toString();
              crdHolder = binding.editTextCardHolder.getText().toString();

              if(!crdNumber.isEmpty() && !validUntil.isEmpty() && !cvv.isEmpty() && !crdHolder.isEmpty()){

                    Payment payment = new Payment(crdNumber,validUntil,cvv,crdHolder);
                    db =FirebaseDatabase.getInstance();
                    reference =db.getReference("Payment");
                    reference.child(newDate).setValue(payment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.editTextCrdNumber.setText("");
                            binding.editTextValidUntil.setText("");
                            binding.editTextCVV.setText("");
                            binding.editTextCardHolder.setText("");
                            Toast.makeText(MainActivity.this,"Paymentrnt Data Succesfuly added ! "+newDate+"",Toast.LENGTH_SHORT).show();
                        }
                    });
                  openViewCardData(v);
              }
              else {
                  Toast.makeText(MainActivity.this,"Fill all details ! ",Toast.LENGTH_SHORT).show();
              }

           }
       });


    }


    //navigation
    public void openViewCardData(View view){
        Intent intent = new Intent(this,ViewCardData.class);

        intent.putExtra("newDate",newDate);

        startActivity(intent);
    }

    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}