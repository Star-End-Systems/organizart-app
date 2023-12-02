package com.example.organizarty.app.party.usecases;

import android.util.Log;

import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.enums.PartyType;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.example.organizarty.infra.api.organizarty.OrganizartyAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetPartiesUseCase {
    private final String _token;

    public GetPartiesUseCase(String token){
        _token = token;
    }

    public Optional<PartyEntity> GetPartyFromId(String id) throws OrganizartyAPIException, IOException {
        OrganizartyAPI api = new OrganizartyAPI(_token);

        return api.getParty(id);
    }

    public List<PartyEntity> GetParties() throws OrganizartyAPIException, IOException {
        OrganizartyAPI api = new OrganizartyAPI(_token);

        return api.getParties();
    }
}
