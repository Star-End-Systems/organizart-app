package com.example.organizarty.infra.api.organizarty;

import com.example.organizarty.infra.api.organizarty.models.PartyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrganizartyPartyEndpoints {
    @GET("/party")
    Call<PartyResponse> getParties();
}
