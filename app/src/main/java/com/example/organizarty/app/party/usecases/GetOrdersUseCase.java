package com.example.organizarty.app.party.usecases;

import com.example.organizarty.enums.ItemStatus;
import com.example.organizarty.enums.ItemType;
import com.example.organizarty.app.party.entities.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class GetOrdersUseCase {
    public static List<OrderEntity> execute(){
        List<OrderEntity> orders = new ArrayList<>();

        orders.add(new OrderEntity("123", 10, "Sem cebola.", ItemStatus.ACCEPTED, "mesa", ItemType.DECORATION));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", ItemStatus.REFUSED, "coxinha", ItemType.FOOD));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", ItemStatus.PENDING, "garçon", ItemType.SERVICE));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", ItemStatus.PENDING, "garçon", ItemType.SERVICE));
        orders.add(new OrderEntity("123", 10, "Sem cebola.", ItemStatus.PENDING, "garçon", ItemType.SERVICE));

        return orders;
    }
}
