package com.example.organizarty.app.main.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import android.os.Bundle;

import com.example.organizarty.R;
import com.example.organizarty.app.party.views.activities.PartyDetailActivity;
import com.example.organizarty.app.party.views.activities.YourOrders;
import com.example.organizarty.app.party.views.adapters.OrderCard;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ArrayAdapter<OrderCard> adapter;
    private final List<OrderCard> pokemonCards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupCards();
    }

    private void setupCards(){

    }

    public void goToDescriptionParty(View view){
        Intent intent = new Intent(this, PartyDetailActivity.class);
        startActivity(intent);
    }

    public void goToYourOrders(View view){
        Intent intent = new Intent(this, YourOrders.class);
        startActivity(intent);
    }

    private void goToDetailsDescriptionActivity(OrderCard o){

    }

}