package com.cpe.mongodb.webblog.model;

import javax.validation.constraints.NotNull;

public class TagModel {
    @NotNull
    private String text;
    @NotNull
    private String color;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
