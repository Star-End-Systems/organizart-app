package com.example.organizarty.app.party.views.adapters;

import com.example.organizarty.app.domain.enums.ItemStatus;
import com.example.organizarty.app.domain.enums.ItemType;

public class OrderCard {
    public String name;
    public String description;
    public ItemType type;
    public ItemStatus status;

    public OrderCard(String name, String description, ItemType type, ItemStatus status){
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
    }
}
