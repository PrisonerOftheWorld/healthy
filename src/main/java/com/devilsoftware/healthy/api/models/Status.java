package com.devilsoftware.healthy.api.models;

public class Status {

    public int code;
    public String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
