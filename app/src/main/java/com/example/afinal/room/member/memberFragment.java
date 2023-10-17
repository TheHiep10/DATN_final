package com.example.afinal.room.member;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.fragment_home.fragmentHome;
import com.example.afinal.fragment_home.them_khu_tro;
import com.example.afinal.fragment_home.them_khu_tro_arr_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class memberFragment extends Fragment {

    static final private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    static final private String PHONE_PATTERN = "^[0-9]{10}$";
    ImageButton confirmbtn;
    RecyclerView recyclerView;
    List<add_member> listmember;
    public int ID = 1;
    DatabaseReference datamember;
    ValueEventListener eventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_member, container, false);
        confirmbtn = mview.findViewById(R.id.btn_add_member);
        datamember = FirebaseDatabase.getInstance().getReference();
        recyclerView = mview.findViewById(R.id.list_member);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        listmember = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(getActivity(), listmember);
        recyclerView.setAdapter(adapter);
        //get dataa from firebase
        datamember = FirebaseDatabase.getInstance().getReference("IdDv1/MEMBER").child(String.valueOf(ID));
        dialog.show();

        eventListener = datamember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listmember.clear();
//                for (DataSnapshot ds: snapshot.getChildren()){
//                    add_member add_Member = ds.getValue(add_member.class);
//                    listmember.add(add_Member);
//                }
                add_member addMember = snapshot.getValue(add_member.class);
                listmember.add(addMember);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", error.getMessage());
                dialog.dismiss();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

        return mview;
    }

    public void uploadData() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_member);

        EditText fullnameMember = dialog.findViewById(R.id.txtname);
        RadioGroup genderMember = dialog.findViewById(R.id.radiosex);
        EditText dateMember = dialog.findViewById(R.id.txtdate);
        EditText addressMember = dialog.findViewById(R.id.txtaddress);
        EditText phoneMember = dialog.findViewById(R.id.txtnumber);
        EditText timeMember = dialog.findViewById(R.id.txtdatestart);
        Button btnaccept = dialog.findViewById(R.id.btn_accept);

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

        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullnameMember.setError(null);
                phoneMember.setError(null);
                addressMember.setError(null);

                RadioButton selectGender = dialog.findViewById(genderMember.getCheckedRadioButtonId());
                String name = fullnameMember.getText().toString().trim();
                String date = dateMember.getText().toString();
                String address = addressMember.getText().toString().trim();
                String phone = phoneMember.getText().toString().trim();
                String time = timeMember.getText().toString();
                boolean cancel = true;
                //check name
                if (name.matches("")){
                    fullnameMember.setError(getString(R.string.error_field_username_empty));
                    cancel = false;
                } else if (!name.matches(USERNAME_PATTERN)) {
                    fullnameMember.setError(getString(R.string.error_field_username_required));
                }
                //check gender
                if (genderMember.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),"Chọn giới tính",Toast.LENGTH_LONG).show();
                    cancel = false;
                }
                //check address
                if (address.matches("")){
                    addressMember.setError(getString(R.string.error_field_nativecountry_empty));
                }
                //check number
                if (phone.matches("")) {
                    phoneMember.setError(getString(R.string.error_field_phone_empty));
                    cancel = false;
                } else if (!phone.matches(PHONE_PATTERN)) {
                    phoneMember.setError(getString(R.string.error_field_phone_required));
                    cancel = false;
                }
                if (cancel) {
                    String gender = selectGender.getText().toString();
                    add_member dataClass = new add_member(name, gender, date, address, time, phone);
                    datamember = FirebaseDatabase.getInstance().getReference("IdDv1/MEMBER");
                    datamember.child(String.valueOf(ID))
                            .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getActivity(), "Hoàn Thành", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    dialog.dismiss();
                    ID++;
                }
            }
        });
        dialog.show();

    }


}