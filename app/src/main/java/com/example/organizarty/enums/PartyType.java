package com.example.organizarty.enums;

import androidx.annotation.NonNull;

import com.example.organizarty.R;

public enum PartyType
{
    chadebebe("Chá de bebe", R.drawable.urso),
    casamento("Casamento", R.drawable.casamento_2),
    aniversario("Aniversário", R.drawable.bolo),
    debutante("Debutante", R.drawable.debutante_2),
    happyHour("Happy Hour", R.drawable.drink);

    private final String name;
    private final int image;

    PartyType(String name, int image) {
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public int getImage(){
        return this.image;
    }
}

