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
import com.example.organizarty.app.party.entities.OrderEntity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.stream.Collectors;

public class ListOrdersAdapters {

    public interface Callback {
        void run(OrderEntity order);
    }

    public static List<View> getCards(AppCompatActivity context, List<OrderEntity> cards, Callback callback){
        LayoutInflater inflater = context.getLayoutInflater();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(20, 20, 20, 20);

        return cards
                .stream()
                .map(order -> {
                    View cardView = inflater.inflate(R.layout.new_order_card, null);

                    CardView card = cardView.findViewById(R.id.new_order_card);

                    TextView txtName = cardView.findViewById(R.id.new_order_card_name);
                    ImageView imgIcon = cardView.findViewById(R.id.new_order_card_icon);

                    String msg = String.format("Produto: %s", order.name);
                    txtName.setText(msg);

                    cardView.setOnClickListener(view -> callback.run(order) );

                    cardView.setOnLongClickListener(view -> {
                        Toast.makeText(context, "Detalhes da festa.", Toast.LENGTH_SHORT).show();
                        return true;
                    });

                    cardView.setLayoutParams(layoutParams);

                    int color = 0;

                    switch (order.status){
                        case WAITING:
                        case PENDING:
                            color = Color.GRAY;
                            imgIcon.setImageResource(R.drawable.look);
                            break;
                        case ACCEPTED:
                            imgIcon.setImageResource(R.drawable.aceito);
                            color = Color.parseColor("#2AD861");
                            break;
                        case REFUSED:
                            color = Color.RED;
                            imgIcon.setImageResource(R.drawable.excluir);
                            break;
                    }

                    card.setBackgroundColor(color);

                    return cardView;
                })
                .collect(Collectors.toList());
    }
}
