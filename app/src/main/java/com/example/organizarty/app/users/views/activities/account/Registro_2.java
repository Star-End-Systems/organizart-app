package com.example.organizarty.app.users.views.activities.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.organizarty.R;

public class Registro_2 extends AppCompatActivity {

    ImageView voltar2, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        voltar2=findViewById(R.id.btn_voltar2);
        registrar=findViewById(R.id.btn_resgitro);

        voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar2 = new Intent(getApplicationContext(), Registro_1.class);
                startActivity(voltar2);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(registrar);
            }
        });
    }
}