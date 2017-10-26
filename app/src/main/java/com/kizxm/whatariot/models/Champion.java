package com.kizxm.whatariot.models;

public class Champion {
    private String name;
    private String id;
    private String hp;
    private String image_url;

    public Champion(String name, String id, String hp, String image_url) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getHp() {
        return hp;
    }

    public String getImage_url() {
        return image_url;
    }
}
