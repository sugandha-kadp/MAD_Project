package com.example.madfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class View_Card_Data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_data);
    }
    //navigation Go Back
    public void goBack (View view){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }

}