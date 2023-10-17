package com.example.afinal.room.member;

import android.widget.ImageView;

public class add_member {
    private String name_member, gender_member, date_Member, address_Member, starttime_member, phone_member;

    public add_member() {

    }
    public add_member(String name_member, String gender_member, String date_Member, String address_Member, String starttime_member, String phone_member) {
        this.name_member = name_member;
        this.gender_member = gender_member;
        this.date_Member = date_Member;
        this.address_Member = address_Member;
        this.starttime_member = starttime_member;
        this.phone_member = phone_member;
    }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public void setGender_member(String gender_member) {
        this.gender_member = gender_member;
    }

    public void setDate_Member(String date_Member) {
        this.date_Member = date_Member;
    }

    public void setAddress_Member(String address_Member) {
        this.address_Member = address_Member;
    }

    public void setStarttime_member(String starttime_member) {
        this.starttime_member = starttime_member;
    }

    public void setPhone_member(String phone_member) {
        this.phone_member = phone_member;
    }

    public String getName_member() {
        return name_member;
    }
    public String getGender_member() {
        return gender_member;
    }
    public String getDate_Member() {
        return date_Member;
    }
    public String getAddress_Member() {
        return address_Member;
    }
    public String getStarttime_member() {
        return starttime_member;
    }
    public String getPhone_member() {
        return phone_member;
    }

}
