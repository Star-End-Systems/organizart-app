package com.example.organizarty.app.users.views.activities.account;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.organizarty.EmptyHome;
import com.example.organizarty.R;
import com.example.organizarty.app.main.views.activities.HomeActivity;

public class LoginActivity extends AppCompatActivity {
    ImageButton SingIn, Criar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SingIn = findViewById(R.id.btn_sing);
        Criar = findViewById(R.id.btn_criar);

        SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent logar = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(logar);
            }
        });


        Criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registro = new Intent(getApplicationContext(), Registro_1.class);
                startActivity(registro);
            }
        });

    }

    public void gotoEmptyHome(View v){
        Intent intent = new Intent(this, EmptyHome.class);
        startActivity(intent);
    }
}