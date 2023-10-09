package com.example.afinal.room.member;

import android.widget.ImageView;

public class add_member {
    private int imageView_sex;
    private String name_member, starttime, textphone;

    public String getName_member() {
        return name_member;
    }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public int getImageView() {
        return imageView_sex;
    }

    public void setImageView(int imageView) {
        imageView_sex = imageView;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getTextphone() {
        return textphone;
    }

    public void setTextphone(String textphone) {
        this.textphone = textphone;
    }

    public add_member(int imageView_sex, String name_member, String starttime, String textphone) {
        this.imageView_sex = imageView_sex;
        this.name_member = name_member;
        this.starttime = starttime;
        this.textphone = textphone;
    }
}
