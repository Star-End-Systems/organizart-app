package com.example.organizarty.app.party.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import com.example.organizarty.R;

public class PartyAdapter extends ArrayAdapter<PartyAdapter.Card> {
    public static class Card{
        public String name;
        public String type;
        public String date;

        public Card(String name, String type, String date){
            this.name = name;
            this.type = type;
            this.date = date;
        }
    }
    public interface GoToParty { void call(Card party); }
    private static final int LAYOUT = R.layout.your_parties_card;
    private final GoToParty onClickListener;
    public PartyAdapter(@NonNull Context context, GoToParty onclickAction) {
        super(context, LAYOUT, new ArrayList<>());
        onClickListener = onclickAction;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(LAYOUT, parent, false);
        }

        Card card = getItem(pos);
        assert card != null;

        TextView txtName = convertView.findViewById(R.id.your_parties_card_name);
        TextView txtDate = convertView.findViewById(R.id.your_parties_card_date);
        TextView txtType = convertView.findViewById(R.id.your_parties_card_type);
        ImageView imgType = convertView.findViewById(R.id.your_parties_card_image);

        txtName.setText(card.name);
        txtType.setText(card.type);
        txtDate.setText(card.date);

        switch (card.type){
            case("Aceito"):
                break;

            case("Recusado"):
                break;
        }

        convertView.setOnClickListener(_v -> onClickListener.call(card));

        return convertView;
    }
}
