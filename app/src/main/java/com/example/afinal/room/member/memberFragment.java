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
    private EditText edtname, edtdate, edtaddress, edtnumber, edtdatestart;
    private ImageButton mimageButton;
    ArrayList<add_member> addmember;
    private ListView listmember;

    static final private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    static final private String PHONE_PATTERN = "^[0-9]{10}$";

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
                edtname = dialog.findViewById(R.id.txtname);
                edtdate = dialog.findViewById(R.id.txtdate);
                RadioGroup rsex = dialog.findViewById(R.id.radiosex);
                RadioButton rman = dialog.findViewById(R.id.nam);
                RadioButton rgirl = dialog.findViewById(R.id.nu);
                edtaddress = dialog.findViewById(R.id.txtaddress);
                edtnumber = dialog.findViewById(R.id.txtnumber);
                edtdatestart = dialog.findViewById(R.id.txtdatestart);
                Button btnaccept = dialog.findViewById(R.id.btn_accept);

                edtname.setError(null);
                edtnumber.setError(null);
                String fullname = edtname.getText().toString().trim();
                String phonenumber = edtnumber.getText().toString().trim();

                btnaccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name, datestart, phone;
                        boolean cancel = true;

                        //check fullname
                        if (fullname.matches("")) {
                            edtname.setError("Nhập Họ và tên");
                        } else if (!fullname.matches(USERNAME_PATTERN)) {
                            edtname.setError(getString(R.string.error_field_username_required));
                            cancel = false;
                        }
                        //check phonenumber
                        if (phonenumber.matches("")) {
                            edtnumber.setError(getString(R.string.error_field_phone_empty));
                            cancel = false;
                        } else if (!phonenumber.matches(PHONE_PATTERN)) {
                            edtnumber.setError(getString(R.string.error_field_phone_required));
                            cancel = false;
                        }

                        if (cancel) {
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