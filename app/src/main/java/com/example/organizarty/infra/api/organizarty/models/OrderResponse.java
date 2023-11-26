package com.example.organizarty.infra.api.organizarty.models;

import com.example.organizarty.enums.ItemStatus;

public class OrderResponse {
    public String name;
    public String description;
    public String type;
    public String note;
    public int quantity;
    public ItemStatus status;

    public OrderResponse() {}
}
