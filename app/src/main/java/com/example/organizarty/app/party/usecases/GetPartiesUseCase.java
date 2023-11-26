package com.example.organizarty.app.party.usecases;

import android.util.Log;

import com.example.organizarty.enums.PartyType;
import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.example.organizarty.infra.api.organizarty.OrganizartyAPI;
import com.example.organizarty.infra.api.organizarty.OrganizartyClient;
import com.example.organizarty.infra.api.organizarty.OrganizartyPartyEndpoints;
import com.example.organizarty.infra.api.organizarty.models.PartyResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPartiesUseCase {
    public static PartyEntity GetPartyFromId(String id){
        PartyEntity party = GetParties().get(0);
        if(party != null){
            return party;
        }

        return new PartyEntity(id, "Festinha do kikozinho", "", PartyType.Aniversario, new ArrayList<>());
    }

    public static List<PartyEntity> GetParties() {
        List<PartyEntity> parties = new ArrayList<>();

        OrganizartyAPI api = new OrganizartyAPI("bunda");

        PartyEntity party = null;
        try {
            party = api.getParty("nada").orElseThrow(Exception::new);
        } catch (OrganizartyAPIException e) {
            e.printStackTrace();
            Log.e("ORGANIZARTY_API", String.valueOf(e.code));
            Log.e("ORGANIZARTY_API", e.body);
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

        parties.add(party);
        parties.add(party);
        parties.add(party);

        return parties;
    }
}
