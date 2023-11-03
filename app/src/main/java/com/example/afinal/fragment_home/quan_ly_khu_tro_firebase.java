package com.example.afinal.fragment_home;

public class quan_ly_khu_tro_firebase {
    private String DiaChi, MaThietBi, TenKhuTro;

    public quan_ly_khu_tro_firebase() {
    }

    public quan_ly_khu_tro_firebase(String diaChi, String maThietBi, String tenKhuTro) {
        this.DiaChi = diaChi;
        this.MaThietBi = maThietBi;
        this.TenKhuTro = tenKhuTro;
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
}
