package com.example.loaneligibilityprediction.data;

public class User {

    String user = "";
    String password = "";
    String email = "";
    public User(
            String user,
            String email,
            String password
    ){
        this.user = user;
        this.password = password;
        this.email = email;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

