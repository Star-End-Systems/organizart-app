package com.example.organizarty.app.users.views.activities.account;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.organizarty.R;
import com.example.organizarty.app.users.usecases.RegisterUserUseCase;

public class Registro_1 extends AppCompatActivity {

    ImageView voltar, continuar;
    private EditText txt_email, txt_username, txt_password, txt_confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);

        setup();

        voltar=findViewById(R.id.btn_voltar);
        continuar=findViewById(R.id.btn_continuar);

        voltar.setOnClickListener(v -> {
            Intent voltar = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(voltar);
        });

        continuar.setOnClickListener(v -> {
            String email = txt_email.getText().toString();
            String username = txt_username.getText().toString();
            String password = txt_password.getText().toString();
            // String confirmPassword = txt_confirmpassword.getText().toString();

            RegisterUserUseCase.SimpleRegister register = new RegisterUserUseCase.SimpleRegister(username, "", "são paulo rio de janeiro", email, password, "");

            Intent intent = new Intent(getApplicationContext(), Registro_2.class);
            intent.putExtra("REGISTER_USER", register);
            startActivity(intent);
        });
    }

    private void setup(){
        txt_email = findViewById(R.id.register_email);
        txt_username = findViewById(R.id.register_username);
        txt_password = findViewById(R.id.register_password);
        txt_confirmpassword = findViewById(R.id.register_confirm_password);
    }

}