package com.example.organizarty.app.users.usecases;

import com.example.organizarty.infra.api.organizarty.OrganizartyAPI;

import java.io.Serializable;

public class RegisterUserUseCase {
    public static class SimpleRegister  implements Serializable
    {
        public String username;
        public String fullname;
        public String location;
        public String email;
        public String password;
        public String cpf;
        public String phone;
        public String img;

        public SimpleRegister(String username, String fullname, String location, String email, String password, String cpf)
        {
            this.username = username;
            this.fullname = fullname;
            this.location = location;
            this.email = email;
            this.password = password;
            this.cpf = cpf;
            this.img = "https://google.com";
            this.phone = "";
        }

        public SimpleRegister(String username, String fullname, String location, String email, String password, String cpf, String phone)
        {
            this.username = username;
            this.fullname = fullname;
            this.location = location;
            this.email = email;
            this.password = password;
            this.cpf = cpf;
            this.img = "https://google.com";
            this.phone = phone;
        }

    }
    public void execute(SimpleRegister registerDto) throws Exception {
        OrganizartyAPI api = new OrganizartyAPI("");

        api.RegisterUser(registerDto);
    }
}
