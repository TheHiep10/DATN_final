package com.example.afinal.room.member;

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
import com.example.afinal.fragment_home.them_khu_tro;

import java.util.ArrayList;
import java.util.List;

public class add_member_arr_adapter extends ArrayAdapter<add_member> {

    Fragment context;

    int id_layout;

    ArrayList<add_member> listmember;

    public add_member_arr_adapter(Fragment context, int id_layout, ArrayList<add_member> listmember) {
        super(context.getContext(), id_layout, listmember);
        this.context = context;
        this.id_layout = id_layout;
        this.listmember = listmember;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = context.getLayoutInflater();
        // Dat id layout len de
        convertView = mInflater.inflate(id_layout, null);
        // selection
        add_member member = listmember.get(position);
        ImageView imageView_sex = convertView.findViewById(R.id.sex);
        imageView_sex.setImageResource(member.getImageView());
        //Declare, reference ID and display on TextView
        TextView name_member = convertView.findViewById(R.id.name_member);
        name_member.setText(member.getName_member());
        TextView time = convertView.findViewById(R.id.starttime);
        time.setText((CharSequence) member.getStarttime());
        TextView phonenumber = convertView.findViewById(R.id.textphone);
        phonenumber.setText(member.getTextphone());

        return convertView;
    }
}
