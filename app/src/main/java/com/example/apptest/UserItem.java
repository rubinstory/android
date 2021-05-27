package com.example.apptest;

public class UserItem {
    private String name;
    private String password;
    private int id;

    public UserItem() {}
    public UserItem(String name, String password, int id) { this.name = name; this.password = password; this.id = id;}

    public String getName() {
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getId() { return this.id; }

    public void setName(String s){
        name = s;
    }
    public void setPassword(String s){
        password = s;
    }
    public void setId(int id) { this.id = id; }
}