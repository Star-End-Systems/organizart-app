package com.example.organizarty.app.main.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import static com.example.organizarty.utils.Async.Fetcher.async;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        async(this::setupPartyCards);
        setupPartyCards();
        setupOrderCards();
    }

    private void setupPartyCards()
    {
        try {
            List<PartyEntity> party = GetPartiesUseCase.GetParties();
            renderpartyCards(party);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void renderpartyCards(List<PartyEntity> parties){
        runOnUiThread(() -> {
            LinearLayout linear = findViewById(R.id.home_your_parties);
            ListPartiesAdapter
                    .getCards(this, parties, this::goToDescriptionParty)
                    .forEach((a) -> {
                        runOnUiThread(() -> linear.addView(a));
                    });
        });
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