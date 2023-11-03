package com.example.afinal.fragment_home;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class fragmentHome extends Fragment {
    private DatabaseReference firebase_home;
    private TextView user_name;
    private String user_id, get_phone;
    private RecyclerView recyclerView;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList = new ArrayList<>();
    private quan_ly_khu_tro_adapter quan_ly_khu_tro_adapter;
    public int count_id_khu_tro;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            count_id_khu_tro = savedInstanceState.getInt("counter");
        }
        else
        {
            count_id_khu_tro = 1;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        MainActivity home_main_activity = (MainActivity) getActivity();
        assert home_main_activity != null;
        View home_view = inflater.inflate(R.layout.fragment_home, container, false);
        // Mapping
        user_name = home_view.findViewById(R.id.welcome_username);
        user_id = home_main_activity.getUserID();
        TextView date = home_view.findViewById(R.id.tv_date);
        TextView soKhuTro = home_view.findViewById(R.id.tv_soKhuTro);
        Button btn_add = home_view.findViewById(R.id.btn_add_khu_tro);
        recyclerView = home_view.findViewById(R.id.rv_add_khu_tro);

        // Code
        if (savedInstanceState != null) {
            count_id_khu_tro = savedInstanceState.getInt("counter");
        } else {
            count_id_khu_tro = 1;
        }

        firebase_home = FirebaseDatabase.getInstance().getReference();
        String current_date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        date.setText(current_date);
        btn_add = home_view.findViewById(R.id.btn_add_khu_tro);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        quan_ly_khu_tro_adapter = new quan_ly_khu_tro_adapter(fragmentHome.this, quan_ly_khu_tro_firebaseArrayList);
        recyclerView.setAdapter(quan_ly_khu_tro_adapter);

        // Hàm con retrieveDataFromFirebase
        retrieveDataFromFirebase();
        firebase_home.child("HOME/SoKhuTro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                soKhuTro.setText(snapshot.getValue().toString());
                count_id_khu_tro = new Integer(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getUserDataByPath(user_id);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_dialog_add_listView(Gravity.CENTER);
            }
        });

        return home_view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Luu trang thai bien counter
        outState.putInt("counter", count_id_khu_tro);
    }

    private void getUserDataByPath(String user_id)
    {
        DatabaseReference firebase_home01 = FirebaseDatabase.getInstance().getReference("USER/PHONE");
        firebase_home01.child(user_id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists())
            {
                DataSnapshot dataSnapshot = task.getResult();
                get_phone = String.valueOf(dataSnapshot.child("userPhone").getValue());
                firebase_home.child("USER/PHONE").child(get_phone).child("userName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user_name.setText(Objects.requireNonNull(snapshot.getValue()).toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void open_dialog_add_listView(int gravity)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_khu_tro_layout);
        EditText edtMaThietBi = dialog.findViewById(R.id.edit_text_ma_thiet_bi);
        EditText edtTenKhuTro = dialog.findViewById(R.id.edit_text_ten_khu_tro);
        EditText edtDiaChi = dialog.findViewById(R.id.edit_text_dia_chi);
        ImageButton imgBtnXacNhan = dialog.findViewById(R.id.img_btn_xac_nhan);
        ImageButton imgBtnHuy = dialog.findViewById(R.id.img_btn_huy);

        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.CENTER == gravity)
        {
            dialog.setCancelable(true);
        }
        else
        {
            dialog.setCancelable(false);
        }

        imgBtnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma_thiet_bi, dia_chi, ten_khu_tro;
                Boolean check_empty = false;

                if (edtMaThietBi.length() == 0)
                {
                    edtMaThietBi.setError("Nhập mã thiết bị");
                    check_empty = true;
                }
                if (edtTenKhuTro.length() == 0)
                {
                    edtTenKhuTro.setError("Nhập tên khu trọ");
                    check_empty = true;
                }
                if (edtDiaChi.length() == 0)
                {
                    edtDiaChi.setError("Nhập địa chỉ");
                    check_empty = true;
                }

                if (check_empty == false)
                {
                    ma_thiet_bi = edtMaThietBi.getText().toString().trim();
                    ten_khu_tro = edtTenKhuTro.getText().toString().trim();
                    dia_chi = edtDiaChi.getText().toString().trim();

                    quan_ly_khu_tro_firebase qlkt = new quan_ly_khu_tro_firebase(dia_chi, ma_thiet_bi, ten_khu_tro);


                    count_id_khu_tro++;
                    firebase_home.child("HOME/SoKhuTro").setValue(count_id_khu_tro);
                    firebase_home.child("Shelter").child("Dv" + count_id_khu_tro).setValue(qlkt);
                    dialog.dismiss();
                }
            }
        });
        imgBtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDiaChi.getText().clear();
                edtMaThietBi.getText().clear();
                edtTenKhuTro.getText().clear();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void retrieveDataFromFirebase()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Shelter");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quan_ly_khu_tro_firebaseArrayList.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    quan_ly_khu_tro_firebase qlkt = childSnapshot.getValue(quan_ly_khu_tro_firebase.class);
                    quan_ly_khu_tro_firebaseArrayList.add(qlkt);
                }

                // Cập nhật bộ điều hợp trước khi đặt nó cho RecyclerView
                quan_ly_khu_tro_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyFragment", "Error retrieving data from Firebase", error.toException());
            }
        });
    }

}
