package com.example.organizarty.app.party.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.organizarty.R;

import java.util.ArrayList;
import java.util.List;

public class PartyOrderAdapter extends ArrayAdapter<PartyOrderCard> {
    private final int LAYOUT = R.layout.party_orders_detail_card;
    public PartyOrderAdapter(@NonNull Context context) {
        super(context, R.layout.party_orders_detail_card, new ArrayList<>());
    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, @NonNull ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(LAYOUT, parent, false);
        }

        PartyOrderCard card = getItem(pos);
        assert card != null;

        ImageView txtImage = convertView.findViewById(R.id.ic_categoria);
        TextView txtNote = convertView.findViewById(R.id.party_order_note);
        TextView txtStatus = convertView.findViewById(R.id.party_order_status);

        txtImage.setImageResource(card.image);
        txtNote.setText(String.valueOf(card.quantity));
        txtStatus.setText(card.status.toString());



        return convertView;
    }
}
