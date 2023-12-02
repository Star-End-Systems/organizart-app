package com.example.organizarty.app.party.entities;

import com.example.organizarty.enums.PartyType;

import java.util.List;

public class PartyEntity {
    public String id;
    public String name;
    public PartyType type;
    public String date;
    public List<OrderEntity> orders;

    public PartyEntity(String id, String name, String date, PartyType type,List<OrderEntity> orders){
        this.id = id;
        this.name = name;
        this.type = type ;

        this.orders = orders;
        this.date = date;
    }
}
