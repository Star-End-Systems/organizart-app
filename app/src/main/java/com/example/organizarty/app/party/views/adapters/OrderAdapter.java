package com.example.organizarty.app.party.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.organizarty.R;
import com.example.organizarty.app.domain.enums.ItemStatus;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<OrderCard> {
    public interface GoToPokemonDetail{ void call(OrderCard pokemon); }
    private final int LAYOUT = R.layout.yourd_order_card;
    private final GoToPokemonDetail onClickListener;
    public OrderAdapter(@NonNull Context context, @NonNull List<OrderCard> objects, GoToPokemonDetail onclickAction) {
        super(context, R.layout.yourd_order_card, objects);
        onClickListener = onclickAction;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(LAYOUT, parent, false);
        }

        OrderCard order = getItem(pos);
        assert order != null;

        TextView txtName = convertView.findViewById(R.id.order_card_name);
        TextView txtDescription = convertView.findViewById(R.id.order_card_description);
        TextView txtStatus = convertView.findViewById(R.id.order_card_status);
        TextView txtType = convertView.findViewById(R.id.order_card_type);
        ImageView imgStatusColor = convertView.findViewById(R.id.order_card_status_color);

        txtName.setText(order.name);
        txtDescription.setText(order.description);
        txtStatus.setText(order.status.toString());
        txtType.setText(order.type.toString());

        int color = 0;

        switch (order.status){
            case WAITING:
            case PENDING:
                color = Color.GRAY;
                break;
            case ACCEPTED:
                color = Color.parseColor("#2AD861");
                break;
            case REFUSED:
                color = Color.RED;
                break;
        }

        imgStatusColor.setColorFilter(color);
        txtStatus.setTextColor(color);

        convertView.setOnClickListener(view -> {
            onClickListener.call(order);
        });

        return convertView;
    }
}
