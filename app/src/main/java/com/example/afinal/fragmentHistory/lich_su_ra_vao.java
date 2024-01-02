package com.example.afinal.fragmentHistory;

public class lich_su_ra_vao {
    private String date, time, key;
    private int user_id;

    lich_su_ra_vao(){

    }

    public lich_su_ra_vao(String date, String key, String time, int user_id) {
        this.date = date;
        this.time = time;
        this.key = key;
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
