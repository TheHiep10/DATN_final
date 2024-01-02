package com.example.afinal.fragment_home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.afinal.MyApp.CHANNEL_ID;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
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
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.example.afinal.room.Manager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class  fragmentHome extends Fragment {
    private DatabaseReference firebase_home;
    private TextView user_name, date, soKhuTro, soThanhVien;
    private Button btn_add;
    private String user_id, get_phone;
    private RecyclerView recyclerView;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList = new ArrayList<>();
    private quan_ly_khu_tro_adapter quan_ly_khu_tro_adapter;
    public int count_id_khu_tro, connection, countMember, action, lock, count, check;
    private Handler handler;
    private static final int UPDATE_INTERVAL = 30000; // Thời gian cập nhật là 30 giây

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        soThanhVien = home_view.findViewById(R.id.countMember);
        btn_add = home_view.findViewById(R.id.btn_add_khu_tro);
        recyclerView = (RecyclerView) home_view.findViewById(R.id.rv_add_khu_tro);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //----------------------//
        // Khởi tạo Handler
        handler = new Handler(Looper.getMainLooper());

        // get user_id in database
        getUserDataByPath(user_id);

        //get number of khu trọ
        updateDataFromFirebase();

        return home_view;
    }
    //KIểm tra mã thiết bị và tên khu trọ
    private void checkDevice(String device){
        ArrayList<quan_ly_khu_tro_firebase> searchList = new ArrayList<>();
        for (quan_ly_khu_tro_firebase quanLyKhuTroFirebase :quan_ly_khu_tro_firebaseArrayList ){
            if (quanLyKhuTroFirebase.getMaThietBi().equals(device)){
                check = 1;
            } else {
                check = 0;
            }
        }
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
    //Thêm Khu trọ mới
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

                ma_thiet_bi = edtMaThietBi.getText().toString().trim();
                ten_khu_tro = edtTenKhuTro.getText().toString().trim();
                dia_chi = edtDiaChi.getText().toString().trim();

                checkDevice(ma_thiet_bi);

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
                if (check == 1)
                {
                    edtMaThietBi.setError(getString(R.string.error_device));
                    check_empty = true;
                }
                if (check_empty == false)
                {
                    connection = lock = countMember = 0;
                    action = 7;

                    quan_ly_khu_tro_firebase qlkt = new quan_ly_khu_tro_firebase(dia_chi, ma_thiet_bi, ten_khu_tro, action, connection, lock, countMember);

                    count_id_khu_tro++;
                    Log.d(TAG,"số khu trọ" + count_id_khu_tro);
                    soKhuTro.setText(String.valueOf(count_id_khu_tro));
                    quan_ly_khu_tro_adapter.setCountId(count_id_khu_tro);
                    firebase_home.child("HOME/SoKhuTro").setValue(count_id_khu_tro);
                    firebase_home.child("DV").child(ma_thiet_bi).setValue(qlkt);
                    firebase_home.child("DV").child(ma_thiet_bi).child("IDtemp").setValue(1);
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
    //Lấy dữ liệu từ Firebase
    private void retrieveDataFromFirebase()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("HOME/SoKhuTro").addListenerForSingleValueEvent(new ValueEventListener() {
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
        ref.child("HOME/SoThanhVien").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long currentCount = snapshot.getValue(Long.class);
                if (currentCount != null)
                {
                    count = currentCount.intValue();
                    soThanhVien.setText(String.valueOf(count));
                    quan_ly_khu_tro_adapter.setCountMember(count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("DV").addValueEventListener(new ValueEventListener() {
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

    //Check connection
    private void checkconnection(){
        for (quan_ly_khu_tro_firebase device : quan_ly_khu_tro_firebaseArrayList){
            String devicecode = device.getMaThietBi();
            if (devicecode != null){
                FirebaseDatabase.getInstance().getReference("DV").child(devicecode).child("action").setValue(7);
            }
        }
    }
    //So Thanh Vien

    private void updateDataFromFirebase() {

        // Code
        firebase_home = FirebaseDatabase.getInstance().getReference();
        String current_date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        date.setText(current_date);

        // Hàm con retrieveDataFromFirebase
        retrieveDataFromFirebase();

        //push data to quan_ly_khu_tro_Adapter.java
        quan_ly_khu_tro_adapter = new quan_ly_khu_tro_adapter(fragmentHome.this, quan_ly_khu_tro_firebaseArrayList,action, count_id_khu_tro, count);
        recyclerView.setAdapter(quan_ly_khu_tro_adapter);



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_id_khu_tro >= 0 || count_id_khu_tro <= 10){
                    open_dialog_add_listView(Gravity.CENTER);
                } else {
                    Toast.makeText(getActivity(), "Khu trọ đã đạt tối đa", Toast.LENGTH_LONG).show();
                }
            }
        });

        checkconnection();

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
