package com.example.organizarty.app.main.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.PartyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListPartiesAdapter {
    public interface Callback {
        void run(PartyEntity party);
    }

    public static List<View> getCards(AppCompatActivity context, List<PartyEntity> cards, Callback callback){
        LayoutInflater inflater = context.getLayoutInflater();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(20, 20, 20, 20);

        return cards.stream().map(party -> {
            View cardView = inflater.inflate(R.layout.party_card, null);

            TextView txtName = cardView.findViewById(R.id.party_card_name);
            txtName.setText(party.name);

            cardView.setOnClickListener(view -> callback.run(party) );

            cardView.setOnLongClickListener(view -> {
                String msg = "Detalhes da festa.";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                return true;
            });

            cardView.setLayoutParams(layoutParams);
            return cardView;
        })
        .collect(Collectors.toList());
    }
}
