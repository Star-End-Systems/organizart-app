package com.example.organizarty.app.party.usecases;

import com.example.organizarty.enums.ItemStatus;
import com.example.organizarty.enums.ItemType;
import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.example.organizarty.infra.api.organizarty.OrganizartyAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetOrdersUseCase {
    private String _token;
    public GetOrdersUseCase(String token ){
        _token = token;
    }

    public  List<OrderEntity> execute() throws OrganizartyAPIException, IOException {
        OrganizartyAPI api = new OrganizartyAPI(_token);

        return api.getOrders();
    }
}
