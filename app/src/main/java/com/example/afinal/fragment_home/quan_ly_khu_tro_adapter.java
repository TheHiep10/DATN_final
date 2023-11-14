package com.example.afinal.fragment_home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.room.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class quan_ly_khu_tro_adapter extends RecyclerView.Adapter<MyViewHolder> {
    private Fragment context;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList;
    public int countId;
    private int connected;
    public quan_ly_khu_tro_adapter(Fragment context, ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList, int countId) {
        this.context = context;
        this.quan_ly_khu_tro_firebaseArrayList = quan_ly_khu_tro_firebaseArrayList;
        this.countId = countId;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using the fragment's context
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_them_khu_tro, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        quan_ly_khu_tro_firebase quanLyKhuTroFirebase = quan_ly_khu_tro_firebaseArrayList.get(position);

        holder.dia_chi.setText(quanLyKhuTroFirebase.getDiaChi());
        holder.ma_thiet_bi.setText(quanLyKhuTroFirebase.getMaThietBi());
        holder.ten_khu_tro.setText(quanLyKhuTroFirebase.getTenKhuTro());
        connected = quanLyKhuTroFirebase.getConnection();
        if (connected == 1)
        {
            holder.imgStatus.setImageResource(R.drawable.icon_connected);
            holder.status.setText("Đã kết nối");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để khởi chạy hoạt đôộng mới
                    Intent intent = new Intent(v.getContext(), Manager.class);

                    // Đặt dữ liệu cần thiết vào Intent
                    intent.putExtra("DEVICE_CODE",quanLyKhuTroFirebase.getMaThietBi());
                    intent.putExtra("NAME_ROOM", quanLyKhuTroFirebase.getTenKhuTro());
                    intent.putExtra("CONNECTION", quanLyKhuTroFirebase.getConnection());
                    // Khởi chạy hoạt động mới
                    v.getContext().startActivities(new Intent[]{intent});
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Create a dialog builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    // Set the title and message of the dialog box
                    builder.setIcon(R.drawable.icon_delete);
                    builder.setTitle("Xóa khu trọ");
                    builder.setMessage("Bạn muốn xóa khu trọ này?");


                    // Add a positive button to the dialog box
                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG,"Thông tin countID" + countId);
                            // Xóa mục khỏi Firebase trước
                            FirebaseDatabase.getInstance().getReference("DV").child(quanLyKhuTroFirebase.getMaThietBi()).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Giảm số lượng phòng xuống và push lên firebase
                                                countId--;
                                                Log.d(TAG, "gia trị countid sau khi giảm" + countId);
                                                FirebaseDatabase.getInstance().getReference().child("HOME/SoKhuTro").setValue(countId);
                                            } else {
                                                // Xử lý khi có lỗi xảy ra trong quá trình xóa
                                                Toast.makeText(holder.itemView.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    });


                    // Show the dialog box
                    builder.show();
                    return false;
                }
            });
        }
        else {
            holder.imgStatus.setImageResource(R.drawable.icon_disconnected);
            holder.status.setText("Chưa kết nối");
        }

    }

    @Override
    public int getItemCount() {
        return quan_ly_khu_tro_firebaseArrayList.size();
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

}

class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView dia_chi, ma_thiet_bi, ten_khu_tro, status;
    ImageView imgStatus;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        dia_chi = itemView.findViewById(R.id.tv_address);
        ma_thiet_bi = itemView.findViewById(R.id.tv_device);
        ten_khu_tro = itemView.findViewById(R.id.tv_ten_khu_tro);
        status = itemView.findViewById(R.id.tv_status);
        imgStatus = itemView.findViewById(R.id.imgView_icon_status);
    }
}

