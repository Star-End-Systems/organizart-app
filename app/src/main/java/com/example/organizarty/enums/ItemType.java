package com.example.organizarty.enums;

import androidx.annotation.NonNull;

public enum ItemType {
    FOOD("Comida"),
    SERVICE("Serviço"),
    DECORATION("Decoração");

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
