package com.spring.fun.controller;

public enum ApiStatus {
    SUCCESS("00"),FAIL("99");

    private String label;

    ApiStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
