package com.example.organizarty.app.party.entities;

import com.example.organizarty.app.domain.enums.ItemStatus;
import com.example.organizarty.app.domain.enums.ItemType;

public class OrderEntity {
    public String id;
    public int quantity;
    public String note;
    public ItemStatus status;
    public String name;
    public ItemType type;

    public OrderEntity(String id, int quantity, String note, ItemStatus status, String name, ItemType type){
        this.id = id;
        this.quantity = quantity;
        this.note = note;
        this.status = status;
        this.name = name;
        this.type = type;
    }
}
