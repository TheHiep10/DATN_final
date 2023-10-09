package com.example.afinal.fragment_home;

public class lich_su_ra_vao {
    private int img_icon_ra_vao;
    private String tv_ten, tv_gio_ra_vao, tv_ngay_ra_vao, tv_ten_khu_tro;

    public int getImg_icon_ra_vao() {
        return img_icon_ra_vao;
    }

    public void setImg_icon_ra_vao(int img_icon_ra_vao) {
        this.img_icon_ra_vao = img_icon_ra_vao;
    }

    public String getTv_ten() {
        return tv_ten;
    }

    public void setTv_ten(String tv_ten) {
        this.tv_ten = tv_ten;
    }

    public String getTv_gio_ra_vao() {
        return tv_gio_ra_vao;
    }

    public void setTv_gio_ra_vao(String tv_gio_ra_vao) {
        this.tv_gio_ra_vao = tv_gio_ra_vao;
    }

    public String getTv_ngay_ra_vao() {
        return tv_ngay_ra_vao;
    }

    public void setTv_ngay_ra_vao(String tv_ngay_ra_vao) {
        this.tv_ngay_ra_vao = tv_ngay_ra_vao;
    }

    public String getTv_ten_khu_tro() {
        return tv_ten_khu_tro;
    }

    public void setTv_ten_khu_tro(String tv_ten_khu_tro) {
        this.tv_ten_khu_tro = tv_ten_khu_tro;
    }
    /*Constructor*/

    public lich_su_ra_vao(int img_icon_ra_vao, String tv_ten, String tv_gio_ra_vao, String tv_ngay_ra_vao, String tv_ten_khu_tro) {
        this.img_icon_ra_vao = img_icon_ra_vao;
        this.tv_ten = tv_ten;
        this.tv_gio_ra_vao = tv_gio_ra_vao;
        this.tv_ngay_ra_vao = tv_ngay_ra_vao;
        this.tv_ten_khu_tro = tv_ten_khu_tro;
    }
}
