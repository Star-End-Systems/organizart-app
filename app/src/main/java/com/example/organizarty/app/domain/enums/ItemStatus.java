package com.example.organizarty.app.domain.enums;

import androidx.annotation.NonNull;

public enum ItemStatus {
    WAITING("Aguardando"),
    PENDING("Pendente"),
    ACCEPTED("Aceito"),
    REFUSED("Recusado");

    private final String name;

    ItemStatus(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
