package com.example.organizarty.infra.api.organizarty;

import android.graphics.Path;

import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrganizartyAPI {
    private final String token;
    private final OkHttpClient _client;

    public OrganizartyAPI(String token){
        this.token = token;
        _client = new OkHttpClient();
    }

    static final String BASE_URL = " https://adeb-45-165-63-52.ngrok-free.app";

    public Optional<PartyEntity> getParty(String partyId) throws OrganizartyAPIException, IOException {
        Request request = new Request
                .Builder()
                .url(String.format("%s/party", BASE_URL))
                .header("Authorization", "Bearer " + token)
                .build();

        int a = 0;

        try{
            try(Response response = _client.newCall(request).execute() ) {
                if(response.body() == null){
                    return Optional.empty();
                }

                String body = response.body().string();

                if (response.code() != 200){
                    throw new OrganizartyAPIException("Something went wrong", response.code(), body);
                }

                Gson gson = new Gson();

                try{
                    PartyEntity party = gson.fromJson(body, PartyEntity.class);
                    return Optional.of(party);
                } catch (Exception _e){
                    return Optional.empty();
                }
            }
        } catch (Exception e){
            String message = e.getMessage();
            return Optional.empty();
        }
    }

    public List<PartyEntity> getParties() throws IOException, OrganizartyAPIException {
        return new ArrayList<>();
    }
}
