package com.industry62.decathlon.dto;

public class SportDto {
    private String id;
    private String name;

    public SportDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
