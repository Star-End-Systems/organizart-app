package com.example.organizarty.activies;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.organizarty.R;
import com.example.organizarty.activies.app.main.views.activities.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    ImageButton SingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SingIn = findViewById(R.id.btn_sing);

        SingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent logar = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(logar);
            }
        });

    }


}