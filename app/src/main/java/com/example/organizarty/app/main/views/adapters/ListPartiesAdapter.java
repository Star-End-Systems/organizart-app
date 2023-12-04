package com.example.organizarty.app.main.views.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.enums.PartyType;

import java.time.format.DateTimeFormatter;
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

            if(party.type == null){
                party.type = PartyType.chadebebe;
            }
            TextView txtName = cardView.findViewById(R.id.party_card_name);
            TextView txtType = cardView.findViewById(R.id.party_card_type);

            ImageView imgIcon = cardView.findViewById(R.id.party_card_icon);
            CardView card = cardView.findViewById(R.id.party_card);

            txtName.setText(party.name);
            txtType.setText(party.name);

            cardView.setOnClickListener(view -> callback.run(party) );

            cardView.setOnLongClickListener(view -> {
                String msg = "Detalhes da festa.";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                return true;
            });

            int color = 0;
            int image = party.type.getImage();

            switch (party.type){
                case chadebebe:
                    color = Color.parseColor("#CB34FF");
                    break;
                case aniversario:
                    color = Color.parseColor("#FF0084");
                    break;
                case happyHour:
                    color = Color.parseColor("#00ff67");
                    break;
                case debutante:
                    color = Color.parseColor("#4c4f56");
                    break;
                case casamento:
                    color = Color.parseColor("#b4a385");
                    break;
            }

            card.setCardBackgroundColor(color);
            imgIcon.setImageResource(image);

            cardView.setLayoutParams(layoutParams);
            return cardView;
        })
        .collect(Collectors.toList());
    }
}
