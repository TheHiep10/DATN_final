package com.example.afinal.fragment_home;

public class quan_ly_khu_tro_firebase {
    private String DiaChi, MaThietBi, TenKhuTro;
    private int Connection, Action, lock, countMember;

    public quan_ly_khu_tro_firebase() {
    }

    public quan_ly_khu_tro_firebase(String diaChi, String maThietBi, String tenKhuTro, int Action ,int connection, int lock, int countMember) {
        this.DiaChi = diaChi;
        this.MaThietBi = maThietBi;
        this.TenKhuTro = tenKhuTro;
        this.Action = Action;
        this.Connection = connection;
        this.lock = lock;
        this.countMember = countMember;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getMaThietBi() {
        return MaThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        MaThietBi = maThietBi;
    }

    public String getTenKhuTro() {
        return TenKhuTro;
    }

    public void setTenKhuTro(String tenKhuTro) {
        TenKhuTro = tenKhuTro;
    }

    public int getAction() {
        return Action;
    }

    public void setAction(int action) {
        Action = action;
    }

    public int getConnection() {
        return Connection;
    }

    public void setConnection(int connection) {
        Connection = connection;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public int getCountMember() {
        return countMember;
    }

    public void setCountMember(int countMember) {
        this.countMember = countMember;
    }
}
