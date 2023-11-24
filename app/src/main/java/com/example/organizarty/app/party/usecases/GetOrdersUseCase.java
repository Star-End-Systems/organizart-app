package com.example.organizarty.app.party.usecases;

import com.example.organizarty.app.party.entities.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class GetOrdersUseCase {
    public static List<OrderEntity> execute(){
        List<OrderEntity> orders = new ArrayList<>();

        orders.add(new OrderEntity("123", 10, "Sem cebola.", "Aceito", "mesa", "decoration"));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", "Recusado", "coxinha", "food"));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", "pendente", "garçon", "service"));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", "pendente", "garçon", "service"));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", "pendente", "garçon", "service"));

        return orders;
    }
}
