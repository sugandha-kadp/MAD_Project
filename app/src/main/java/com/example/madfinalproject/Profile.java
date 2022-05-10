package com.example.madfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinalproject.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    String name,email,address,number,password;
    Button updatebutton,deletebutton,feedbackbutton;
    AlertDialog.Builder builder;

    TextView tvName;
    TextView tvEmail;
    TextView tvNumber;
    TextView tvPassword;
    TextView tvAddress;

    ActivityProfileBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updatebutton =findViewById(R.id.updatebutton);
        deletebutton =findViewById(R.id.deletebutton);
        feedbackbutton = findViewById(R.id.feedbackbutton);
        builder = new AlertDialog.Builder(this);

         tvName=findViewById(R.id.tvName);
         tvEmail=findViewById(R.id.tvEmail);
         tvNumber=findViewById(R.id.tvNumber);
         tvPassword=findViewById(R.id.tvPassword);
         tvAddress=findViewById(R.id.tvAddress);



         binding.feedbackbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openFeedBack();
             }
         });

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        viewProfileActivity(name);

        binding.updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditCustomerData(v);
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Remove")
                        .setMessage("Are you sure you want to remove Customer Data ? ")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                deleteCustomerData(name);
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

        binding.updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditSuccesfull(v);
            }
        });

    }

    private void openFeedBack() {
        Intent intent = new Intent(this,AddFeedback.class);
        startActivity(intent);

    }

    private void deleteCustomerData(String name) {
        reference = FirebaseDatabase.getInstance().getReference("Customer");
        reference.child(name).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    goBack();
                    Toast.makeText(Profile.this, "Customer Removed From the System", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Profile.this, "Customer Could Not From the System", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void viewProfileActivity(String name) {
        reference = FirebaseDatabase.getInstance().getReference("Customer");
        reference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        Toast.makeText(Profile.this, "Successfully Get Data From Database", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        email = String.valueOf(dataSnapshot.child("email").getValue());
                        address = String.valueOf(dataSnapshot.child("address").getValue());
                        number = String.valueOf(dataSnapshot.child("number").getValue());
                        password = String.valueOf(dataSnapshot.child("password").getValue());

                        binding.tvAddress.setText("Customer Address : " +address);
                        binding.tvEmail.setText("Customer Email : " +email);
                        binding.tvName.setText("Customer Name :" +name);
                        binding.tvNumber.setText("Customer Mobile number :" +number);
                        binding.tvPassword.setText("Customer Password :" +password);

                    } else {
                        Toast.makeText(Profile.this, "Customer Data does not exist", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Profile.this, "Failed to Get Data From Database", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void EditCustomerData(View view) {

        Intent intent = new Intent(this, Update.class);

        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("address", address);
        intent.putExtra("validUntil", number);
        intent.putExtra("crdHolder", password);

        startActivity(intent);

    }


    public void openEditSuccesfull(View view) {

        Intent intent = new Intent(this, Update.class);

        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("address", address);
        intent.putExtra("validUntil", number);
        intent.putExtra("crdHolder", password);

        startActivity(intent);

    }

    //navigation Go Back
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}