package com.example.organizarty.app.users.entities;

public class UserEntity {
    public String username;
    public String fullname;
    public String location;
    public String email;
    public String cpf;
    public String image;
    public String token;

    public UserEntity(String username, String fullname, String location, String email, String cpf, String image)
    {
        this.username = username;
        this.fullname = fullname;
        this.location = location;
        this.email = email;
        this.cpf = cpf;
        this.image = image;
        this.token = "";
    }

    public UserEntity(String name, String email, String token)
    {
        this.fullname = name;
        this.email = email;
        this.token = token;
        this.username = name;
        this.location = "";
        this.cpf = "";
        this.image = "";
    }
}
