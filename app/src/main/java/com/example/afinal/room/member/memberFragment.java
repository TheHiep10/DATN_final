package com.example.afinal.room.member;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.afinal.R;
import com.example.afinal.fragment_home.fragmentHome;
import com.example.afinal.fragment_home.them_khu_tro;
import com.example.afinal.fragment_home.them_khu_tro_arr_adapter;

import java.util.ArrayList;

public class memberFragment extends Fragment {
    private ImageButton mimageButton;
    ArrayList<add_member> addmember;
    private ListView listmember;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_member, container, false);
        mimageButton = mview.findViewById(R.id.btn_add_member);
        listmember = mview.findViewById(R.id.list_member);
        addmember = new ArrayList<>();
        add_member_arr_adapter addMemberArrAdapter = new add_member_arr_adapter(memberFragment.this, R.layout.infomation_member, addmember);


        mimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_member);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = Gravity.CENTER;
                window.setAttributes(windowAttributes);
                if (Gravity.CENTER == Gravity.CENTER) {
                    dialog.setCancelable(true);
                } else {
                    dialog.setCancelable(false);
                }
                EditText edtname = dialog.findViewById(R.id.txtname);
                EditText edtdate = dialog.findViewById(R.id.txtdate);
                RadioGroup rsex = dialog.findViewById(R.id.radiosex);
                RadioButton rman = dialog.findViewById(R.id.nam);
                RadioButton rgirl = dialog.findViewById(R.id.nu);
                EditText edtaddress = dialog.findViewById(R.id.txtaddress);
                EditText edtnumber = dialog.findViewById(R.id.txtnumber);
                EditText edtdatestart = dialog.findViewById(R.id.txtdatestart);
                Button btnaccept = dialog.findViewById(R.id.btn_accept);


                btnaccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name, datestart, phone;

                        if (edtname.length() == 0)
                        {
                            edtname.setError("Nhập họ và tên");
                        }
                        else if (edtnumber.length() < 10 || edtnumber.length() > 10) {
                            edtnumber.setError("Chỉ nhập đủ 10 số");
                        }
                        else
                        {
                            name = edtname.getText().toString();
                            datestart = edtdatestart.getText().toString();
                            phone = edtnumber.getText().toString();
                            listmember.setAdapter(addMemberArrAdapter);
                            if (rman.isChecked())
                            {
                                addmember.add(new add_member( R.drawable.men, name, datestart,phone ));
                            }
                            else if (rgirl.isChecked())
                            {
                                addmember.add(new add_member( R.drawable.girl, name, datestart,phone ));
                            }
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

        return mview;
    }

}