package com.example.organizarty.app.party.entities;

import java.util.List;

public class PartyEntity {
    public String id;
    public String name;
    public String type;
    public List<OrderEntity> orders;

    public PartyEntity(String id, String name, String type,List<OrderEntity> orders){
        this.id = id;
        this.name = name;
        this.type = type;
        this.orders = orders;
    }
}
