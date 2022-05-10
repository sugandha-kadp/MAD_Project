package com.example.madfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener{


    private TextInputEditText etEmail,etPassword;
    private Button btnSignIn;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        etEmail = (TextInputEditText) findViewById(R.id.signinEmailTextField);
        etEmail.setOnClickListener(this);

        etPassword = (TextInputEditText) findViewById(R.id.signinPasswordTextField);
        etPassword.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView4:
                //startActivity(new Intent(this,Profile.class));
                openProfile(v);
                break;

            case R.id.btnSignIn:
                userLogin();
                break;
        }
    }
    private void userLogin(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            etEmail.setError("Please provide a valid email");
//            etEmail.requestFocus();
//            return;
//        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Minimum password length is 6 characters");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to user profile
                    //startActivity(new Intent(Login.this, Profile.class));
                    //openProfile();
                }
                else{
                    Toast.makeText(Login.this, "Failed to login", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void openProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void goBack (View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }




}