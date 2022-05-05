package com.example.madfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {


 DatabaseReference adddataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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