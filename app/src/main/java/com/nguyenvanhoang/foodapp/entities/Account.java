package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private String keyID;
    private String email;
    private String passWord;
    private String role;

    public Account(String keyID) {
        this.keyID = keyID;
    }

    public Account(String keyID, String email, String passWord, String role) {
        this.keyID = keyID;
        this.email = email;
        this.passWord = passWord;
        this.role = role;
    }

    public Account() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getEmail().equals(account.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
