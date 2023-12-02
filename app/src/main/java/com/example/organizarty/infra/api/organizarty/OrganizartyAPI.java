package com.example.organizarty.infra.api.organizarty;

import android.graphics.Path;

import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.users.usecases.LoginUserUsecase;
import com.example.organizarty.app.users.usecases.RegisterUserUseCase;
import com.example.organizarty.enums.PartyType;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrganizartyAPI {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final String token;
    private final OkHttpClient _client;

    public OrganizartyAPI(String token){
        this.token = token;
        _client = new OkHttpClient();
    }

    public class LoginUserResponse{
        public String id;
        public String token;
        public String fullname;
        public String email;
        public String username;
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

    // TODO: Orders from party
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

    public void RegisterUser(RegisterUserUseCase.SimpleRegister registerDto) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(registerDto);

        RequestBody requestBody = RequestBody.create(JSON, json);

        String url = String.format("%s/User/Register", BASE_URL);

        Request request = new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build();

        try(Response response = _client.newCall(request).execute() ) {
            if(response.body() == null){
                return;
            }

            String body = response.body().string();

            if (response.code() != 200){
                throw new OrganizartyAPIException("Something went wrong", response.code(), body);
            }

            try{
                OrdersResponse parties = gson.fromJson(body, OrdersResponse.class);
                return;
            } catch (Exception _e){
                throw new Exception("Invalid response");
            }
        }

    }

    public LoginUserResponse LoginUser(LoginUserUsecase.SimpleLogin loginDto) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(loginDto);

        RequestBody requestBody = RequestBody.create(JSON, json);

        String url = String.format("%s/User/Login", BASE_URL);

        Request request = new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build();

        try(Response response = _client.newCall(request).execute() ) {
            if(response.body() == null){
                throw new OrganizartyAPIException("Something went wrong", response.code(), "");
            }

            String body = response.body().string();

            if (response.code() != 200){
                throw new OrganizartyAPIException("Something went wrong", response.code(), body);
            }

            try{
                return gson.fromJson(body, LoginUserResponse.class);
            } catch (Exception _e){
                throw new Exception("Invalid response");
            }
        }

    }
}
