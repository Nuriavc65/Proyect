package com.example.duckduck;

import com.google.firebase.database.DatabaseReference;

public class Users {

    String FullName, Email , Password;

    long nuHorasDormir, nuHorasTrabajo;

    public Users() {

    }

    public Users(String fullName, String email, String password, long nHorasDormir,long nHorasTrabajo) {
        FullName = fullName;
        Email = email;
        Password = password;
        nuHorasDormir = nHorasDormir;
        nuHorasTrabajo = nHorasTrabajo;
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

    public void setnuHorasDormir(long nHorasDormir){ nuHorasDormir = nHorasDormir; }

    public void setnuHorasTrabajo(long nHorasTrabajo){nuHorasTrabajo = nHorasTrabajo;}

    public long getnuHorasDormir(){return nuHorasDormir;}
    public long getnuHorasTrabajo(){return nuHorasTrabajo;}

    //DatabaseReference usersRef = ref.child("users");
}
