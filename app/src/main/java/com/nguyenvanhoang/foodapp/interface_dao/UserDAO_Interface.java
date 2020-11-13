package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.User;

import java.util.List;

public interface UserDAO_Interface {
    public DatabaseReference getAllUser();
    public DatabaseReference addUser(User user);
    public DatabaseReference updateUser(User user);
    public DatabaseReference deleteUser(User user);
}
