package com.example.organizarty.exceptions;

public class AnauthenticatedUserException extends Exception {
    public AnauthenticatedUserException(){
        super("Please login");
    }
}
