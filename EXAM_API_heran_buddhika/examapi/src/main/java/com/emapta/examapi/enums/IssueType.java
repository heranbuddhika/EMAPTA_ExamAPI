package com.emapta.examapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueType {
    BUG(0, "bug"),
    STORY(1, "story"),
    TASK(2, "task");

    private final int value;
    @JsonValue
    private final String text;

    private IssueType(int value, String text) {
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
