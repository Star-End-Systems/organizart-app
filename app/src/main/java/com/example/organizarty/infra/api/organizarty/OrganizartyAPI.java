package com.example.organizarty.infra.api.organizarty;

import android.graphics.Path;

import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.enums.PartyType;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static class PartiesResponse{
        public List<PartyEntity> data;
    }

    public static class OrdersResponse{
        public List<OrderEntity> data;
    }

    static final String BASE_URL = "https://organizarty-api.onrender.com/api";

    public Optional<PartyEntity> getParty(String partyId) throws OrganizartyAPIException, IOException {
        String url = String.format("%s/Schedule/Party/%s", BASE_URL, partyId);
        Request request = new Request
                .Builder()
                .url(url)
                .header("Authorization", "Bearer " + token)
                .build();

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
                party.type = PartyType.BabyTea;
                return Optional.of(party);
            } catch (Exception _e){
                return Optional.empty();
            }
        }
    }

    public List<PartyEntity> getParties() throws IOException, OrganizartyAPIException {
        Request request = new Request
                .Builder()
                .url(String.format("%s/Schedule/Parties", BASE_URL))
                .header("Authorization", "Bearer " + token)
                .build();

        try(Response response = _client.newCall(request).execute() ) {
            if(response.body() == null){
                return new ArrayList<>();
            }

            String body = response.body().string();

            if (response.code() != 200){
                throw new OrganizartyAPIException("Something went wrong", response.code(), body);
            }

            Gson gson = new Gson();

            try{
                PartiesResponse parties = gson.fromJson(body, PartiesResponse.class);
                return parties.data.stream().peek(x -> x.type = PartyType.BabyTea).collect(Collectors.toList());
            } catch (Exception _e){
                return new ArrayList<>();
            }
        }
    }

    public List<OrderEntity> getOrders() throws IOException, OrganizartyAPIException {
        Request request = new Request
                .Builder()
                .url(String.format("%s/Schedule/Orders", BASE_URL))
                .header("Authorization", "Bearer " + token)
                .build();

        try(Response response = _client.newCall(request).execute() ) {
            if(response.body() == null){
                return new ArrayList<>();
            }

            String body = response.body().string();

            if (response.code() != 200){
                throw new OrganizartyAPIException("Something went wrong", response.code(), body);
            }

            Gson gson = new Gson();

            try{
                OrdersResponse parties = gson.fromJson(body, OrdersResponse.class);
                return parties.data;
            } catch (Exception _e){
                return new ArrayList<>();
            }
        }
    }
}
