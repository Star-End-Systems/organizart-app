package com.example.organizarty.app.party.views.activities;

import static com.example.organizarty.utils.Async.Fetcher.async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetOrdersUseCase;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.party.views.adapters.OrderAdapter;
import com.example.organizarty.app.party.views.adapters.OrderCard;
import com.example.organizarty.app.party.views.adapters.PartyAdapter;
import com.example.organizarty.exceptions.OrganizartyAPIException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMoreParty extends AppCompatActivity {

    private ArrayAdapter<PartyAdapter.Card> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_party);

        setup();
        async(this::fetchParties, this::renderPartyCards, this::handleError);
    }

    private void setup(){
        adapter = new PartyAdapter(this, this::goToDescriptionParty);

        GridView gridView = findViewById(R.id.show_more_party_grid);

        gridView.setAdapter(adapter);
    }

    private void handleError(Exception e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void goToDescriptionParty(PartyAdapter.Card party){
        Intent intent = new Intent(this, PartyDetailActivity.class);

        intent.putExtra("PARTY_ID", party.id);

        startActivity(intent);
    }

    private void renderPartyCards(List<PartyEntity> parties){
            addCards(parties
                    .stream()
                    .map(x -> new PartyAdapter.Card(x.id, x.name, x.type, x.date))
                    .collect(Collectors.toList()));
    }

    private List<PartyEntity> fetchParties() throws OrganizartyAPIException, IOException {
        return GetPartiesUseCase.GetParties();
    }

    private void addCards(List<PartyAdapter.Card> cards){
        runOnUiThread(() ->{
            adapter.addAll(cards);
            adapter.notifyDataSetChanged();
        });
    }
}