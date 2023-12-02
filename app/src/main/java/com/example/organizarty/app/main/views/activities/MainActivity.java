package com.example.organizarty.app.main.views.activities;
import static com.example.organizarty.utils.Async.Fetcher.async;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.example.organizarty.R;
import com.example.organizarty.app.party.usecases.GetPartiesUseCase;
import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.app.users.views.activities.account.LoginActivity;
import com.example.organizarty.utils.storage.PreferencesUtils;

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
             new GetPartiesUseCase(user.token).GetParties();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private void goToLoggedState(boolean isLogged){
        runOnUiThread(() -> {
            if(isLogged){
                goToHome();
            } else{
                goToLogin();
            }
        });
    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}