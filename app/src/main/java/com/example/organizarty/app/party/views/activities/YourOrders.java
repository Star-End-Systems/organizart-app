package com.example.organizarty.app.party.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.organizarty.R;
import com.example.organizarty.app.main.views.activities.HomeActivity;
import com.example.organizarty.app.party.usecases.GetOrdersUseCase;
import com.example.organizarty.app.party.views.adapters.OrderAdapter;
import com.example.organizarty.app.party.views.adapters.OrderCard;
import static com.example.organizarty.utils.Async.Fetcher.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YourOrders extends AppCompatActivity {
    private ArrayAdapter<OrderCard> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
        setup();
        async(this::initialFetch);
    }

    private void setup(){
        adapter = new OrderAdapter(this, new ArrayList<>(), this::goToCardDescriptioon);

        GridView gridView = findViewById(R.id.all_orders_grid);

        gridView.setAdapter(adapter);
    }

    private void initialFetch(){
        addCards(GetOrdersUseCase
                .execute()
                .stream()
                .map(x -> new OrderCard(x.name, x.id,x.type, x.status))
                .collect(Collectors.toList()));
    }

    private void addCards(List<OrderCard> cards){
        runOnUiThread(() ->{
            adapter.addAll(cards);
            adapter.notifyDataSetChanged();
        });
    }

    public void goToHome(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToHomeBunda(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }



    private void goToCardDescriptioon(OrderCard card){

    }
}