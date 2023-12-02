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
    public static Optional<PartyEntity> GetPartyFromId(String id) throws OrganizartyAPIException, IOException {
        OrganizartyAPI api = new OrganizartyAPI("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6Imx1LWNzcyIsIm5hbWVpZCI6IjA4ZGJmMjlhLTYwN2UtNDU0ZC04ZjBjLWQwYmY4NTNkZDIwNSIsInJvbGUiOiJDbGllbnQiLCJuYmYiOjE3MDE1MjUxODQsImV4cCI6MTcwMTYxMTU4NCwiaWF0IjoxNzAxNTI1MTg0fQ.UqGltwRhoy-cP6vcPW3gGOQUmKeh13KY4usccX7r19Q");

        return api.getParty(id);
    }

    public static List<PartyEntity> GetParties() throws OrganizartyAPIException, IOException {
        OrganizartyAPI api = new OrganizartyAPI("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6Imx1LWNzcyIsIm5hbWVpZCI6IjA4ZGJmMjlhLTYwN2UtNDU0ZC04ZjBjLWQwYmY4NTNkZDIwNSIsInJvbGUiOiJDbGllbnQiLCJuYmYiOjE3MDE1MjUxODQsImV4cCI6MTcwMTYxMTU4NCwiaWF0IjoxNzAxNTI1MTg0fQ.UqGltwRhoy-cP6vcPW3gGOQUmKeh13KY4usccX7r19Q");

        return api.getParties();
    }
}
