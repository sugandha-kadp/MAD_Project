package com.example.madfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinalproject.databinding.ActivityUpdateDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Update extends AppCompatActivity {

    EditText eTNameUpdate;
    EditText eTEmailUpdate;
    EditText eTNumberUpdate;
    EditText eTPasswordUpdate;
    EditText eTAddressUpdate;

    String name,email,address,number,password;

    ActivityUpdateDetailsBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eTNameUpdate = findViewById(R.id.eTNameUpdate);
        eTEmailUpdate= findViewById(R.id.eTEmailUpdate);
        eTNumberUpdate= findViewById(R.id.eTNumberUpdate);
        eTPasswordUpdate= findViewById(R.id.eTPasswordUpdate);
        eTAddressUpdate= findViewById(R.id.eTAddressUpdate);

        Intent intent =  getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");
        number = intent.getStringExtra("validUntil");
        password = intent.getStringExtra("crdHolder");

        getCustomerData();


    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goBackProfile();

                String nameNew = binding.eTNameUpdate.getText().toString();
                String newEmail =binding.eTEmailUpdate.getText().toString();
                String newAddress = binding.eTAddressUpdate.getText().toString();
                String newNumber = binding.eTNumberUpdate.getText().toString();
                String newPassword = binding.eTPasswordUpdate.getText().toString();

                editCustomer(nameNew,newEmail,newAddress,newNumber,newPassword);

            }
        });

    }

    private void editCustomer(String nameNew, String newEmail, String newAddress, String newNumber, String newPassword) {
        HashMap Customer = new HashMap();
        Customer.put("name",nameNew);
        Customer.put("email",newEmail);
        Customer.put("address",newAddress);
        Customer.put("number",newNumber);
        Customer.put("password",newPassword);

        reference = FirebaseDatabase.getInstance().getReference("Customer");
        reference.child(name).updateChildren(Customer).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    goBackProfile();

                    binding.eTNameUpdate.setText("");
                    binding.eTEmailUpdate.setText("");
                    binding.eTAddressUpdate.setText("");
                    binding.eTNumberUpdate.setText("");
                    binding.eTPasswordUpdate.setText("");

                    Toast.makeText(Update.this,"Customer Updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Update.this,"Failed to update Customer",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goBackProfile() {
        Intent intent = new Intent(this,Profile.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }


    private void getCustomerData() {
        binding.eTNameUpdate.setText(name);
        binding.eTEmailUpdate.setText(email);
        binding.eTAddressUpdate.setText(address);
        binding.eTNumberUpdate.setText(number);
        binding.eTPasswordUpdate.setText(password);

    }
}