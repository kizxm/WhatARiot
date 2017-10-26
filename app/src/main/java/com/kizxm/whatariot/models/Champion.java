package com.kizxm.whatariot.models;

public class Champion {
    private String name;
    private String id;
    private String image_url;

    public Champion(String name, String id, String image_url) {
        this.name = name;
        this.id = id;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }
}
