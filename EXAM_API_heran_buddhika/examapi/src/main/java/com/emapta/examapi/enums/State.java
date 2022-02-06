package com.emapta.examapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    OPEN(0, "open"),
    IN_PROGRESS(1, "in_progress"),
    TESTING(2, "testing"),
    DEPLOY(3, "deploy");

    private final int value;
    @JsonValue
    private final String text;

    private State(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
}
