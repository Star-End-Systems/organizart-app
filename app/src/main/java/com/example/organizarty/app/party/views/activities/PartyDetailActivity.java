package com.example.organizarty.app.party.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.party.views.adapters.PartyOrderAdapter;
import com.example.organizarty.app.party.views.adapters.PartyOrderCard;
import static com.example.organizarty.utils.Async.Fetcher.async;

import java.util.List;
import java.util.stream.Collectors;

public class PartyDetailActivity extends AppCompatActivity {
    private ArrayAdapter<PartyOrderCard> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        setup();
        async(this::initialFetch);
    }

    void setup(){
        adapter = new PartyOrderAdapter(this);

        GridView gridView = findViewById(R.id.party_detail_orders);

        gridView.setAdapter(adapter);
    }

    void initialFetch(){
        PartyEntity party = GetPartiesUseCase.GetPartyFromId("4002");
        renderScreen(party);
    }

    void renderScreen(PartyEntity party){
        runOnUiThread(() -> {
            TextView txtName = findViewById(R.id.party_detail_name);
            TextView txtType = findViewById(R.id.party_detail_type);
            TextView txtId = findViewById(R.id.party_detail_id);
            ImageView txtImage = findViewById(R.id.party_detail_image);

            txtName.setText(party.name);
            txtType.setText(party.type.toString());
            txtId.setText(party.id);

            List<PartyOrderCard> cards = party.orders
                    .stream()
                    .map(x -> new PartyOrderCard(x.name, x.quantity, x.status))
                    .collect(Collectors.toList());


            adapter.addAll(cards);
            adapter.notifyDataSetChanged();
        });
    }
}