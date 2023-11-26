package com.example.organizarty.exceptions;

public class OrganizartyAPIException extends Exception{
    public String body;
    public int code;

    public OrganizartyAPIException(String msg, int code, String body){
        super(msg);

        this.code = code;
        this.body = body;
    }
}
