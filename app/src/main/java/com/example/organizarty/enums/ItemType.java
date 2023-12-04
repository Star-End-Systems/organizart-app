package com.example.organizarty.enums;

import androidx.annotation.NonNull;

import com.example.organizarty.R;

public enum ItemType {
    Food("Comida", R.drawable.img_produto),
    Service("Serviço", R.drawable.img_servico),
    Decoration("Decoração", R.drawable.img_decoracao);

    private final String name;
    private final int image;


    ItemType(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getImage(){
        return image;
    }


    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
