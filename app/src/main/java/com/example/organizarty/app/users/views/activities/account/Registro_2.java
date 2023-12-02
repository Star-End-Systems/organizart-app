package com.example.organizarty.app.users.views.activities.account;

import static com.example.organizarty.utils.Async.Fetcher.async;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.organizarty.R;
import com.example.organizarty.app.users.usecases.RegisterUserUseCase;

public class Registro_2 extends AppCompatActivity {

    ImageView voltar2, registrar;
    RegisterUserUseCase.SimpleRegister register;

    private EditText txt_nome, txt_phone, txt_cpf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        register = (RegisterUserUseCase.SimpleRegister) getIntent().getSerializableExtra("REGISTER_USER");

        voltar2=findViewById(R.id.btn_voltar2);
        registrar=findViewById(R.id.btn_resgitro);

        txt_nome = findViewById(R.id.register_email);
        txt_phone = findViewById(R.id.register_username);
        txt_cpf = findViewById(R.id.register_password);

        voltar2.setOnClickListener(v -> {
            Intent voltar2 = new Intent(getApplicationContext(), Registro_1.class);
            startActivity(voltar2);
        });

        registrar.setOnClickListener(v -> {
            String nome = txt_nome.getText().toString();
            String phone = txt_phone.getText().toString();
            String cpf = txt_cpf.getText().toString();

            register.fullname = nome;
            register.phone = phone;
            register.cpf = cpf;

            async(() -> executeRegister(register), this::onRegisterSuccess, this::handleError);
        });
    }

    private void onRegisterSuccess(boolean ok){
        runOnUiThread((() -> {
            Intent registrar = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(registrar);
        }));
    }

    private void handleError(Exception e){
        runOnUiThread((() -> {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }));
    }

    private boolean executeRegister(RegisterUserUseCase.SimpleRegister register) throws Exception {
        new RegisterUserUseCase().execute(register);
        return true;
    }
}