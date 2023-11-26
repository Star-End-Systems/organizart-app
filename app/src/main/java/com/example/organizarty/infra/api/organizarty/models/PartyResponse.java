package com.example.organizarty.infra.api.organizarty.models;

import java.util.List;

public class PartyResponse {
    public String name;
    public String type;
    public String date;
    public List<OrderResponse> orders;
    public PartyResponse(){}
}
