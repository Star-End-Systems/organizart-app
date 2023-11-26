package com.example.organizarty.app.domain.enums;

import androidx.annotation.NonNull;

public enum PartyType {
        BabyTea("Chá de bebê"),
        Casamento("Casamento"),
        Aniversario("Aniversário");

        private final String name;

        PartyType(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
    }
}
