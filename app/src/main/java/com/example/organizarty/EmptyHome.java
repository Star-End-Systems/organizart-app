package com.example.organizarty;

import static com.example.organizarty.app.components.NavComponents.setupNav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.organizarty.app.users.views.activities.account.LoginActivity;
import com.example.organizarty.exceptions.AnauthenticatedUserException;
import com.example.organizarty.utils.storage.PreferencesUtils;

public class EmptyHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_home);
        setup();
    }

    public void setup(){
        PreferencesUtils preferences = new PreferencesUtils(this);

        try {
            preferences.readOrganizartyAuthToken();
            setupNav(this, preferences);
        } catch (AnauthenticatedUserException e) {
            goToLogin();
        }
    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}