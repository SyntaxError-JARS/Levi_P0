package com.revature.banking.models;

public class user {
    String email;
    String firstName;
    String lastName;
    String password;

    public user(String firstName, String lastName, String email, String password){
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public user() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "User{" +
                "fname='" + firstName + '\'' +
                ", lname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

