package com.example.afinal.fragment_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.afinal.R;

import java.util.ArrayList;

public class lich_su_ra_vao_arr_adapter extends ArrayAdapter<lich_su_ra_vao>
{
    Fragment context;
    int id_layout;
    ArrayList<lich_su_ra_vao> lich_su_ra_vao_list;

    public lich_su_ra_vao_arr_adapter(@NonNull Context context, int resource, Fragment context1, int id_layout, ArrayList<lich_su_ra_vao> lich_su_ra_vao_list) {
        super(context, resource);
        this.context = context1;
        this.id_layout = id_layout;
        this.lich_su_ra_vao_list = lich_su_ra_vao_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = context.getLayoutInflater();
        convertView = mInflater.inflate(id_layout, null);
        lich_su_ra_vao mLichSu = lich_su_ra_vao_list.get(position);
        ImageView img_icon_ra_vao = convertView.findViewById(R.id.img_icon_ra_vao);
        img_icon_ra_vao.setImageResource(mLichSu.getImg_icon_ra_vao());
        TextView tv_ten_khach_thue = convertView.findViewById(R.id.tv_ten_khach_thue);
        tv_ten_khach_thue.setText(mLichSu.getTv_ten());
        TextView tv_ten_khu_tro = convertView.findViewById(R.id.tv_name_khu_tro);
        tv_ten_khu_tro.setText(mLichSu.getTv_ten_khu_tro());
        TextView tv_gio_ra_vao = convertView.findViewById(R.id.tv_gio_ra_vao);
        tv_gio_ra_vao.setText(mLichSu.getTv_gio_ra_vao());
        TextView tv_ngay_ra_vao = convertView.findViewById(R.id.tv_ngay_ra_vao);
        tv_ngay_ra_vao.setText(mLichSu.getTv_ngay_ra_vao());
        return convertView;
    }
}
