package com.example.androidds;

public class Users {
    private String Uid;
    private String Name;
    private String PhoneNumber;
    private String Email;
    private String Password;
    private String type;

    public Users() {

    }

    public Users(String uid, String name, String phoneNumber, String email, String password, String type) {
        Uid = uid;
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
        this.type = type;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
