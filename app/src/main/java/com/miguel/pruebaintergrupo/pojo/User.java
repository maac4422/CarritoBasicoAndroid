package com.miguel.pruebaintergrupo.pojo;

/**
 * Created by miguel on 9/14/16.
 */
public class User {
    int id;
    String username,password;



    public void User(String username,String password){
        this.username = username;
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
