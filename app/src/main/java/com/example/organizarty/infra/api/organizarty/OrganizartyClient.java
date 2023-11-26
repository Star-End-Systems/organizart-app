package com.example.organizarty.infra.api.organizarty;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrganizartyClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("(insert latest version)")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
