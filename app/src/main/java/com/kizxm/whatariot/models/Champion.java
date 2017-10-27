package com.kizxm.whatariot.models;

import org.parceler.Parcel;

@Parcel
public class Champion {
    private String name;
    private String id;
    private String hp;
    private String mp;
    private String movespeed;
    private String image_url;
    private String big_image_url;


    public Champion() {}

    public Champion(String name, String id, String hp, String mp, String movespeed, String image_url, String big_image_url) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.mp = mp;
        this.movespeed = movespeed;
        this.image_url = image_url;
        this.big_image_url = big_image_url;
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

    public String getMp() { return mp;}

    public String getMovespeed() { return movespeed; }

    public String getBig_image_url() { return big_image_url; }

    public String getImage_url() {
        return image_url;
    }
}
