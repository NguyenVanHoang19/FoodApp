package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.Account;
import com.nguyenvanhoang.foodapp.entities.User;

import java.util.List;

public interface AccountDAO_Interface {
    public DatabaseReference getAllAccount();
    public DatabaseReference addAccount(Account account);
    public DatabaseReference updateAccount(Account account);
    public DatabaseReference deleteAccount(Account account);
}
