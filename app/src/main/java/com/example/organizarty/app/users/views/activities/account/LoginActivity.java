package com.example.organizarty.app.users.views.activities.account;
import static com.example.organizarty.utils.Async.Fetcher.async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.organizarty.R;
import com.example.organizarty.app.main.views.activities.HomeActivity;
import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.app.users.usecases.LoginUserUsecase;
import com.example.organizarty.exceptions.OrganizartyAPIException;
import com.example.organizarty.utils.storage.PreferencesUtils;

public class LoginActivity extends AppCompatActivity {
    PreferencesUtils _preferences;
    EditText txt_email, txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupClickers();
        setup();

    }

    private void setup(){
        _preferences = new PreferencesUtils(this);

        txt_email = findViewById(R.id.register_email);
        txt_password = findViewById(R.id.txt_senha);
    }

    private void setupClickers(){
        ImageButton SingIn = findViewById(R.id.btn_sing);
        ImageButton Criar = findViewById(R.id.btn_criar);

        SingIn.setOnClickListener(v -> login());

        Criar.setOnClickListener(v -> {
            Intent registro = new Intent(getApplicationContext(), Registro_1.class);
            startActivity(registro);
        });
    }

    private void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void login() {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        async(() -> executeLogin(email, password), this::onLoginSuccess, this::onLoginFail);
    }

    private void onLoginSuccess(UserEntity user){
        runOnUiThread(() -> {
            _preferences.writeOrganizartyUserInfo(user.token, user.username, user.email);
            goToHome();
        });
    }

    private void onLoginFail(Exception e){
        runOnUiThread(() -> {
            if(e instanceof OrganizartyAPIException){
                OrganizartyAPIException err = (OrganizartyAPIException) e;
                String message = String.format("%s | %s | %s", e.getMessage(), ((OrganizartyAPIException) e).body, ((OrganizartyAPIException) e).code);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private UserEntity executeLogin(String email, String password) throws Exception {
        return new LoginUserUsecase().execute(new LoginUserUsecase.SimpleLogin(email, password));
    }

}