package com.example.afinal.room.member;

import android.widget.ImageView;

public class add_member {
    private int imageView_gender;
    private String name_member, gender_member, starttime_member, phone_member;

    public int getImageView_gender() {
        return imageView_gender;
    }

    public void setImageView_gender(int imageView_gender) {
        this.imageView_gender = imageView_gender;
    }

    public String getName_member() {
        return name_member;
    }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public String getStarttime_member() {
        return starttime_member;
    }

    public void setStarttime_member(String starttime_member) {
        this.starttime_member = starttime_member;
    }

    public String getPhone_member() {
        return phone_member;
    }

    public void setPhone_member(String phone_member) {
        this.phone_member = phone_member;
    }

    public String getGender_member() {
        return gender_member;
    }

    public void setGender_member(String gender_member) {
        this.gender_member = gender_member;
    }

    public add_member(int imageView_gender, String name_member, String starttime_member, String phone_member) {
        this.imageView_gender = imageView_gender;
        this.name_member = name_member;
        this.starttime_member = starttime_member;
        this.phone_member = phone_member;
    }

    public add_member(String name_member, String gender_member, String starttime_member, String phone_member) {
        this.name_member = name_member;
        this.gender_member = gender_member;
        this.starttime_member = starttime_member;
        this.phone_member = phone_member;
    }
}
