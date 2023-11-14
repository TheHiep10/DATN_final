package com.example.afinal.fragment_home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.afinal.room.Manager;
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
    private TextView user_name, date, soKhuTro;
    private Button btn_add;
    private String user_id, get_phone;
    private RecyclerView recyclerView;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList = new ArrayList<>();
    private quan_ly_khu_tro_adapter quan_ly_khu_tro_adapter;
    public int count_id_khu_tro, IDtemp, connection;
    private Handler handler;
    private static final int UPDATE_INTERVAL = 15000; // Thời gian cập nhật là 30 giây

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        date = home_view.findViewById(R.id.tv_date);
        soKhuTro = home_view.findViewById(R.id.tv_soKhuTro);
        btn_add = home_view.findViewById(R.id.btn_add_khu_tro);
        recyclerView = home_view.findViewById(R.id.rv_add_khu_tro);
        //----------------------//
        // Khởi tạo Handler
        handler = new Handler(Looper.getMainLooper());

        //get number of khu trọ
        updateDataFromFirebase();

        return home_view;
    }

    //Lấy dữ liệu người dùng từ Firebase
    private void getUserDataByPath(String user_id)
    {
        DatabaseReference firebase_home01 = FirebaseDatabase.getInstance().getReference("USER/PHONE");
        firebase_home01.child(user_id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists())
            {
                DataSnapshot dataSnapshot = task.getResult();
                get_phone = String.valueOf(dataSnapshot.child("userPhone").getValue());
                if (get_phone != null)
                {
                    firebase_home.child("USER/PHONE").child(get_phone).child("userName").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            user_name.setText(Objects.requireNonNull(snapshot.getValue()).toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else {Log.e(TAG,"Khong nhan duoc du lieu tu firebase");}
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
                    connection = IDtemp = 0;
                    ma_thiet_bi = edtMaThietBi.getText().toString().trim();
                    ten_khu_tro = edtTenKhuTro.getText().toString().trim();
                    dia_chi = edtDiaChi.getText().toString().trim();

                    quan_ly_khu_tro_firebase qlkt = new quan_ly_khu_tro_firebase(dia_chi, ma_thiet_bi, ten_khu_tro, connection);

                    count_id_khu_tro++;
                    Log.d(TAG,"số khu trọ" + count_id_khu_tro);
                    soKhuTro.setText(String.valueOf(count_id_khu_tro));
                    quan_ly_khu_tro_adapter.setCountId(count_id_khu_tro);
                    firebase_home.child("HOME/SoKhuTro").setValue(count_id_khu_tro);
                    firebase_home.child("DV").child(ma_thiet_bi).setValue(qlkt);
                    firebase_home.child("DV").child(ma_thiet_bi).child("IDtemp").setValue(IDtemp);
                    firebase_home.child("DV").child(ma_thiet_bi).child("Connection").setValue(connection);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DV");
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

    private void updateDataFromFirebase(){

        // Code
        firebase_home = FirebaseDatabase.getInstance().getReference();
        String current_date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        date.setText(current_date);

        // Hàm con retrieveDataFromFirebase
        retrieveDataFromFirebase();

        //push data to quan_ly_khu_tro_Adapter.java
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        quan_ly_khu_tro_adapter = new quan_ly_khu_tro_adapter(fragmentHome.this, quan_ly_khu_tro_firebaseArrayList, count_id_khu_tro);
        recyclerView.setAdapter(quan_ly_khu_tro_adapter);
        firebase_home = FirebaseDatabase.getInstance().getReference();
        firebase_home.child("HOME/SoKhuTro").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long currentID = snapshot.getValue(Long.class);
                if (currentID != null)
                {
                    count_id_khu_tro = currentID.intValue();
                    soKhuTro.setText(String.valueOf(count_id_khu_tro));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // get user_id in database
        getUserDataByPath(user_id);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_dialog_add_listView(Gravity.CENTER);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateDataFromFirebase();
            }
        },UPDATE_INTERVAL);
    }

    @Override
    public void onDestroy() {
        // Hủy lịch trình khi Activity bị hủy
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
