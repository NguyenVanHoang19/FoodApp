package com.nguyenvanhoang.foodapp.dao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.User;
import com.nguyenvanhoang.foodapp.interface_dao.UserDAO_Interface;
public class UserDAO implements UserDAO_Interface {
    private  FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private  DatabaseReference databaseReference = firebaseDatabase.getReference("user");
    @Override
    public DatabaseReference getAllUser() {
        return databaseReference;
    }

    @Override
    public  DatabaseReference  addUser(User user) {
        String keyID = databaseReference.push().getKey();
        user.setKeyID(keyID);
        databaseReference.child(user.getKeyID()).setValue(user);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateUser(User user) {
        databaseReference.child(user.getKeyID()).setValue(user);
        return databaseReference;
    }

    @Override
    public DatabaseReference deleteUser(User user) {
        databaseReference.child(user.getEmailUser()).removeValue();
        return databaseReference;
    }
}
