package com.example.organizarty.app.party.entities;

public class OrderEntity {
    public String id;
    public int quantity;
    public String note;
    public String status;
    public String name;
    public String type;

    public OrderEntity(String id, int quantity, String note, String status, String name, String type){
        this.id = id;
        this.quantity = quantity;
        this.note = note;
        this.status = status;
        this.name = name;
        this.type = type;
    }
}
