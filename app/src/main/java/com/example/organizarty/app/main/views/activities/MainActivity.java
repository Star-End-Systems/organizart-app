package com.example.organizarty.app.main.views.activities;
import static com.example.organizarty.utils.Async.Fetcher.async;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.example.organizarty.EmptyHome;
import com.example.organizarty.R;
import com.example.organizarty.app.party.entities.PartyEntity;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.app.users.views.activities.account.LoginActivity;
import com.example.organizarty.utils.storage.PreferencesUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PreferencesUtils _preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();

        new Handler().postDelayed( () ->{
                async(this::isUserLogged, this::goToLoggedState, e -> goToLogin());
        }, 3000);
    }

    private void setup(){
        _preferences = new PreferencesUtils(this);
    }

    private boolean isUserLogged()
    {
        try{
            UserEntity user = _preferences.readOrganizartyAuthToken();
            List<PartyEntity> parties = new GetPartiesUseCase(user.token).GetParties();

            if(parties.isEmpty()){
                runOnUiThread(() -> {
                    goToEmpty();
                    finish();
                });
                return false;
            }

            return true;
        } catch (Exception e){
            runOnUiThread(() -> {
                goToLogin();
                finish();
            });

            return false;
        }
    }

    private void goToLoggedState(boolean isLogged){

    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToEmpty(){
        Intent intent = new Intent(this, EmptyHome.class);
        startActivity(intent);
    }


    private void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}