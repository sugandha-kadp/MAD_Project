package com.example.madfinalproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPayment {
    private DatabaseReference databaseReference;

    public DAOPayment(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Payment.class.getSimpleName());
    }

    public Task<Void> add(Payment payment){

//        if(payment==null)
        return databaseReference.push().setValue(payment);

    }
}
