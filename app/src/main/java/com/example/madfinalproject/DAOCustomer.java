package com.example.madfinalproject;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOCustomer {
    private DatabaseReference databaseReference;

    public DAOCustomer(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Customer.class.getSimpleName());
    }

    public Task<Void> add(Customer customer){

//        if(payment==null)
        return databaseReference.push().setValue(customer);

    }
}
