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

import java.util.List;
import java.util.stream.Collectors;

public class ShowMoreParty extends AppCompatActivity {

    private ArrayAdapter<PartyAdapter.Card> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_party);

        setup();
        initialFetch();
    }

    private void setup(){
        adapter = new PartyAdapter(this, this::goToDescriptionParty);

        GridView gridView = findViewById(R.id.show_more_party_grid);

        gridView.setAdapter(adapter);
    }

    public void goToDescriptionParty(PartyAdapter.Card party){
        Intent intent = new Intent(this, PartyDetailActivity.class);
        startActivity(intent);
    }

    private void initialFetch(){
        async(() -> {
            try {
                addCards(GetPartiesUseCase
                        .GetParties()
                        .stream()
                        .map(x -> new PartyAdapter.Card(x.name, x.type, x.id))
                        .collect(Collectors.toList()));
            } catch (Exception e) {
                runOnUiThread(() ->
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        });
    }

    private void addCards(List<PartyAdapter.Card> cards){
        runOnUiThread(() ->{
            adapter.addAll(cards);
            adapter.notifyDataSetChanged();
        });
    }
}