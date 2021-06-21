package com.example.apptest;

public class UserItem { // 사용자 정보를 저장하기 위한 클래스
    private String name; // 사용자의 이름을 저장하는 변수
    private String password; // 사용자의 비밀번호를 저장하는 변수
    private String image; // 사용자의 이미지 URL을 저장하는 변수
    private int id; // 사용자의 id 값을 저장하기 위한 변수

    public UserItem() {}
    public UserItem(String name, String password, int id) { this.name = name; this.password = password; this.id = id;}
    public UserItem(String name, String password, String image, int id) { this.name = name; this.password = password; this.image = image; this.id = id;}

    public String getName() {
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getId() { return this.id; }
    public String getImage(){ return this.image; }

    public void setName(String s){
        name = s;
    }
    public void setPassword(String s){
        password = s;
    }
    public void setId(int id) { this.id = id; }
    public void setImage(String image){ this.image = image; }
}