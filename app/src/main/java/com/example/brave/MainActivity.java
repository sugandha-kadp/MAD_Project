package com.example.brave;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.brave.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String name,email,address,number,password;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                name = binding.txtname.getText().toString();
//                email = binding.Email.getText().toString();
//                address = binding.address.getText().toString();
//                number = binding.number.getText().toString();
//                password = binding.password.getText().toString();

                if(!name.isEmpty()&& !email.isEmpty() && !address.isEmpty() && !number.isEmpty() && !password.isEmpty()){
                    Customer customer = new Customer(name,email,address,number,password);
                    db = FirebaseDatabase.getInstance();
                    //reference = db.getReference(path);
                }

            }
        });
    }
}