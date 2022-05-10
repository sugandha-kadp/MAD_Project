package com.example.madfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.madfinalproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String name,email,address,telNumber,password;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = binding.etName.getText().toString();
                email = binding.etEmail.getText().toString();
                address = binding.etAddress.getText().toString();
                telNumber = binding.etTelephoneNumber.getText().toString();
                password = binding.etPassword.getText().toString();


                if (!name.isEmpty() && !email.isEmpty() && !address.isEmpty() && !telNumber.isEmpty() && !password.isEmpty()) {

                    Customer customer = new Customer(name, email, address, telNumber, password);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Customer");



//                    if (TextUtils.isEmpty(name)) {
//                        etName.name("Email is required");
//                        etName.requestFocus();
//                        return;
//                    }
//



                    reference.child(name).setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.etName.setText("");
                            binding.etEmail.setText("");
                            binding.etAddress.setText("");
                            binding.etTelephoneNumber.setText("");
                            binding.etPassword.setText("");

                            Toast.makeText(MainActivity.this, "New Customer Successfully Registered ! ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    openProfileActivity(v);

                } else {
                    Toast.makeText(MainActivity.this, "Fill all required details ! ", Toast.LENGTH_SHORT).show();
                }

            }
        });

               binding.textViewlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openlogin(v);

           }
       });
    }

    private void openlogin(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }




    public void openProfileActivity(View view){
            Intent intent = new Intent(this,Profile.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }

        //navigation Go Back
        public void goBack (View view){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);





    }


}