package com.example.organizarty.app.party.usecases;

import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;

import java.util.ArrayList;
import java.util.List;

public class GetPartiesUseCase {
    public static PartyEntity GetPartyFromId(String id){
        List<OrderEntity> orders = GetOrdersUseCase.execute();
        orders.addAll(GetOrdersUseCase.execute());

        return new PartyEntity(id, "Festinha do kikozinho", "Chá de bebe", orders);
    }

    public static List<PartyEntity> GetParties(){
        List<PartyEntity> parties = new ArrayList<>();
        PartyEntity party = GetPartyFromId("");

        parties.add(party);
        parties.add(party);
        parties.add(party);

        return parties;
    }
}
