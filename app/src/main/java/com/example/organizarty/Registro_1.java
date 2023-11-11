package com.example.organizarty;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.organizarty.activies.LoginActivity;
import com.example.organizarty.activies.app.main.views.activities.HomeActivity;

public class Registro_1 extends AppCompatActivity {

    ImageView voltar, continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);


        voltar=findViewById(R.id.btn_voltar);
        continuar=findViewById(R.id.btn_continuar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent voltar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(voltar);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continuar = new Intent(getApplicationContext(), Registro_2.class);
                startActivity(continuar);
            }
        });
    }
}