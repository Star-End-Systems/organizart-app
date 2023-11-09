package com.example.organizarty.activies.app.main.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.organizarty.R;

import java.util.List;


public class OrderAdapter extends ArrayAdapter<OrderCard> {
    public interface GoToPokemonDetail{ void call(OrderCard pokemon); }
    private GoToPokemonDetail onClickListener;
    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<OrderCard> objects, GoToPokemonDetail onclickAction) {
        super(context, resource, objects);
        onClickListener = onclickAction;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_card, parent, false);
        }

        OrderCard order = getItem(pos);

        TextView txt_pokemonName = convertView.findViewById(R.id.pokemon_name);
//        TextView txt_pokemonNumber = convertView.findViewById(R.id.pokemon_number);
//        ImageView img_pokemon = convertView.findViewById(R.id.img_pokemon);
//        ImageView img_pokemonType1 = convertView.findViewById(R.id.pokemon_type_1);
//        ImageView img_pokemonType2 = convertView.findViewById(R.id.pokemon_type_2);
//
        txt_pokemonName.setText(order.name);
//        txt_pokemonNumber.setText(pokemon.pokedexEntry);
//        Picasso.get().load(pokemon.imageSpriteUrl).into(img_pokemon);
//
//        img_pokemonType1.setVisibility(View.INVISIBLE);
//        img_pokemonType2.setVisibility(View.INVISIBLE);


        convertView.setOnClickListener(view -> {
            onClickListener.call(order);
        });

        return convertView;
    }
}
