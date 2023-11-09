package com.example.organizarty.activies.app.main.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.organizarty.R;
import com.example.organizarty.activies.app.main.views.adapters.OrderAdapter;
import com.example.organizarty.activies.app.main.views.adapters.OrderCard;

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

    private void goToDetailsDescriptionActivity(OrderCard o){

    }

}