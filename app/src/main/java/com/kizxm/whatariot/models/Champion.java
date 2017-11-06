package com.kizxm.whatariot.models;

import org.parceler.Parcel;

@Parcel
public class Champion {
    String name;
    String id;
    String hp;
    String mp;
    String moveSpeed;
    String image_url;
    String big_image_url;
    private String pushId;


    public Champion() {}

    public Champion(String name, String id, String hp, String mp, String moveSpeed, String image_url, String big_image_url) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.mp = mp;
        this.moveSpeed = moveSpeed;
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

    public String getMoveSpeed() { return moveSpeed; }

    public String getBig_image_url() { return big_image_url; }

    public String getImage_url() {
        return image_url;
    }

    public String getPushId() { return pushId;  }

    public void setPushId(String pushId) {  this.pushId = pushId;   }
}
