package com.example.organizarty.app.users.usecases;

import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.infra.api.organizarty.OrganizartyAPI;

public class LoginUserUsecase {
    public static class SimpleLogin{
        public String email;
        public String password;

        public SimpleLogin(String email, String password){
            this.email = email;
            this.password = password;
        }
    }

    public UserEntity execute(SimpleLogin loginDto) throws Exception {
        OrganizartyAPI api = new OrganizartyAPI("");

        OrganizartyAPI.LoginUserResponse response = api.LoginUser(loginDto);

        return new UserEntity(response.fullname, response.email, response.token);
    }
}
