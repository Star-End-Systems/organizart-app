package com.example.organizarty.app.main.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.organizarty.R;
import com.example.organizarty.app.main.views.adapters.ListOrdersAdapters;
import com.example.organizarty.app.main.views.adapters.ListPartiesAdapter;
import com.example.organizarty.app.party.entities.OrderEntity;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetOrdersUseCase;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.party.views.activities.PartyDetailActivity;
import com.example.organizarty.app.party.views.activities.ShowMoreParty;
import com.example.organizarty.app.party.views.activities.YourOrders;
import com.example.organizarty.app.party.views.adapters.OrderCard;
import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.app.users.views.activities.account.LoginActivity;
import com.example.organizarty.exceptions.AnauthenticatedUserException;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.example.organizarty.utils.storage.PreferencesUtils;

import static com.example.organizarty.utils.Async.Fetcher.async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {
    private PreferencesUtils _preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setup();

        async(this::setupPartyCards, this::renderpartyCards, this::handleError);
        async(this::setupOrderCards, this::renderOrderCards, this::handleError);
    }

    private void setup(){
        _preferences = new PreferencesUtils(this);
    }

    private List<PartyEntity> setupPartyCards() throws OrganizartyAPIException, IOException, AnauthenticatedUserException {
        UserEntity user = _preferences.readOrganizartyAuthToken();
        return new GetPartiesUseCase(user.token).GetParties();
    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void renderpartyCards(List<PartyEntity> parties){
        runOnUiThread(() -> {
            LinearLayout linear = findViewById(R.id.home_your_parties);
            ListPartiesAdapter
                    .getCards(this, parties, this::goToDescriptionParty)
                    .forEach(linear::addView);
        });
    }

    private void handleError(Exception e){
        runOnUiThread(() ->{
            if(e instanceof AnauthenticatedUserException){
                goToLogin();
                return;
            }

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        );
    }

    private List<OrderEntity> setupOrderCards() throws OrganizartyAPIException, IOException {
         return GetOrdersUseCase.execute()
                 .stream()
                 .limit(2)
                 .collect(Collectors.toList());
    }

    private void renderOrderCards(List<OrderEntity> orders){
        runOnUiThread(() -> {
            LinearLayout linear = findViewById(R.id.home_order_list);

            ListOrdersAdapters
                    .getCards(this, orders, this::goToYourOrders)
                    .forEach(linear::addView);
        });

    }

    public void goToDescriptionParty(PartyEntity party){
        Intent intent = new Intent(this, PartyDetailActivity.class);

        intent.putExtra("PARTY_ID", party.id);

        startActivity(intent);
    }

    public void goToYourOrders(View view){
        Intent intent = new Intent(this, YourOrders.class);
        startActivity(intent);
    }

    public void goToMorePartie(View v){
        Intent intent = new Intent(this, ShowMoreParty.class);
        startActivity(intent);
    }

    public void goToYourOrders(OrderEntity order){
        Intent intent = new Intent(this, YourOrders.class);
        startActivity(intent);
    }

    private void goToDetailsDescriptionActivity(OrderCard o){

    }

}