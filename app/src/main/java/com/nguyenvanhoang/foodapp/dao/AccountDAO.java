package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.Account;
import com.nguyenvanhoang.foodapp.interface_dao.AccountDAO_Interface;

public class AccountDAO implements AccountDAO_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("account");
    @Override
    public DatabaseReference getAllAccount() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addAccount(Account account) {
        String keyID = databaseReference.push().getKey();
        account.setKeyID(keyID);
        databaseReference.child(account.getKeyID()).setValue(account);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateAccount(Account account) {
        databaseReference.child(account.getEmail()).setValue(account);
        return databaseReference;
    }

    @Override
    public DatabaseReference deleteAccount(Account account) {
        databaseReference.child(account.getEmail()).removeValue();
        return databaseReference;
    }
}
