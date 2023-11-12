package com.example.organizarty.activies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizarty.R;
import com.example.organizarty.activies.app.main.views.activities.HomeActivity;

public class YourOrders extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
    }

    public void goToHome(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}