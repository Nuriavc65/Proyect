package com.example.duckduck;

public class Users {

    String FullName, Email , Password, confirmPasswpord;

    public Users() {

    }

    public Users(String fullName, String email, String password, String confirmPasswpord) {
        FullName = fullName;
        Email = email;
        Password = password;
        this.confirmPasswpord = confirmPasswpord;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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

    public String getConfirmPasswpord() {
        return confirmPasswpord;
    }

    public void setConfirmPasswpord(String confirmPasswpord) {
        this.confirmPasswpord = confirmPasswpord;
    }
}
