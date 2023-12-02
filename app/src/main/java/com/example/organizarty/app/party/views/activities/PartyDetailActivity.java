package com.example.organizarty.app.party.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetOrdersUseCase;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.party.views.adapters.PartyOrderAdapter;
import com.example.organizarty.app.party.views.adapters.PartyOrderCard;
import com.example.organizarty.exceptions.NotFoundException;
import com.example.organizarty.exceptions.OrganizartyAPIException;

import static com.example.organizarty.utils.Async.Fetcher.async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartyDetailActivity extends AppCompatActivity {
    private ArrayAdapter<PartyOrderCard> adapter;
    private String PartyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        setup();
        async(this::initialFetch, this::renderScreen, this::handleError);
        async(this::fetchOrderCards, this::renderOrders, this::handleError);
    }

    void setup(){
        Intent intent = getIntent();
        GridView gridView = findViewById(R.id.party_detail_orders);

        adapter = new PartyOrderAdapter(this);

        gridView.setAdapter(adapter);

        PartyId = intent.getStringExtra("PARTY_ID");
    }

    PartyEntity initialFetch() throws OrganizartyAPIException, IOException, NotFoundException {
        Optional<PartyEntity> p = GetPartiesUseCase.GetPartyFromId(PartyId);

        if(!p.isPresent()){
            throw new NotFoundException("Party not found");
        }

        return p.get();
    }

    private List<OrderEntity> fetchOrderCards() throws OrganizartyAPIException, IOException {
        return GetOrdersUseCase.execute();
    }

    void renderScreen(PartyEntity party){
        runOnUiThread(() -> renderParty(party));
    }

    void handleError(Exception e){
        runOnUiThread(() -> {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    void renderParty(PartyEntity party){
        TextView txtName = findViewById(R.id.party_detail_name);
        TextView txtType = findViewById(R.id.party_detail_type);
        TextView txtId = findViewById(R.id.party_detail_id);
        ImageView txtImage = findViewById(R.id.party_detail_image);

        txtName.setText(party.name);
        txtType.setText(party.type.toString());
        txtId.setText(party.id);
    }

    private void renderOrders(List<OrderEntity> orders){
        runOnUiThread(() -> {
            List<PartyOrderCard> cards = orders
                    .stream()
                    .map(x -> new PartyOrderCard(x.name, x.quantity, x.status))
                    .collect(Collectors.toList());


            adapter.addAll(cards);
            adapter.notifyDataSetChanged();
        });
    }
}