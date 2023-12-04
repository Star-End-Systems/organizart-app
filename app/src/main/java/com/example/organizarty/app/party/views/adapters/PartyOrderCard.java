package com.example.organizarty.app.party.views.adapters;

import com.example.organizarty.enums.ItemStatus;

public class PartyOrderCard {
    public String name;
    public int quantity;
    public ItemStatus status;
    public int image;

    public PartyOrderCard(String name, int quantity, ItemStatus status, int image){
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
    }
}
