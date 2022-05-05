package com.example.madfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DatabaseReference;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {


 DatabaseReference adddataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText crdNumber = findViewById(R.id.editTextCrdNumber);
        final EditText validUntil= findViewById(R.id.editTextValidUntil);
        final EditText cvv= findViewById(R.id.editTextCVV);
        final EditText crdHolder= findViewById(R.id.editTextCardHolder);

        Button btn_confirm =findViewById(R.id.btn_confirm);
        DAOPayment dao = new DAOPayment();

        btn_confirm.setOnClickListener(v ->
        {
             Payment payment = new Payment(crdNumber.getText().toString(),validUntil.getText().toString(),cvv.getText().toString(),crdHolder.getText().toString());
             dao.add(payment).addOnSuccessListener(suc ->
             {
                 Toast.makeText(this,"Payment Data Succesfully Added",Toast.LENGTH_SHORT).show();
             }).addOnFailureListener(er ->
             {
                 Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
             });

            Intent intent = new Intent(this,View_Card_Data.class);

            startActivity(intent);

        });


    }




    //navigation
    public void openViewCardData(View view){
        Intent intent = new Intent(this,View_Card_Data.class);

        startActivity(intent);
    }

    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}