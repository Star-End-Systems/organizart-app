package com.example.organizarty.app.main.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.organizarty.R;
import com.example.organizarty.app.main.views.adapters.ListOrdersAdapters;
import com.example.organizarty.app.main.views.adapters.ListPartiesAdapter;
import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetOrdersUseCase;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.party.views.activities.PartyDetailActivity;
import com.example.organizarty.app.party.views.activities.ShowMoreParty;
import com.example.organizarty.app.party.views.activities.YourOrders;
import com.example.organizarty.app.party.views.adapters.OrderCard;

import java.util.List;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupPartyCards();
        setupOrderCards();
    }

    private void setupPartyCards()
    {
        LinearLayout linear = findViewById(R.id.home_your_parties);

        List<PartyEntity> party = GetPartiesUseCase.GetParties();

        ListPartiesAdapter
                .getCards(this, party, this::goToDescriptionParty)
                .forEach(linear::addView);
    }

    private void setupOrderCards(){
        LinearLayout linear = findViewById(R.id.home_order_list);

        List<OrderEntity> orders = GetOrdersUseCase.execute().stream().limit(2).collect(Collectors.toList());

        ListOrdersAdapters
                .getCards(this, orders, this::goToYourOrders)
                .forEach(linear::addView);
    }

    public void goToDescriptionParty(PartyEntity party){
        Intent intent = new Intent(this, PartyDetailActivity.class);
        startActivity(intent);
    }

    public void goToYourOrders(View view){
        Intent intent = new Intent(this, YourOrders.class);
        startActivity(intent);
    }

    public void goToMorePartie(View v){
        Intent intent = new Intent(this, ShowMoreParty.class);
        startActivity(intent);
    }

    public void goToYourOrders(OrderEntity order){
        Intent intent = new Intent(this, YourOrders.class);
        startActivity(intent);
    }

    private void goToDetailsDescriptionActivity(OrderCard o){

    }

}