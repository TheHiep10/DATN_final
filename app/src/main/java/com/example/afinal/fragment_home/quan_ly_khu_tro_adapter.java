package com.example.afinal.fragment_home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.login.forgotPassWord;
import com.example.afinal.room.Manager;
import com.example.afinal.room.member.DetailActivity;
import com.example.afinal.room.member.add_member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class quan_ly_khu_tro_adapter extends RecyclerView.Adapter<MyViewHolder> {
    private Fragment context;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList;
    public int countId;
    public quan_ly_khu_tro_adapter(Fragment context, ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList) {
        this.context = context;
        this.quan_ly_khu_tro_firebaseArrayList = quan_ly_khu_tro_firebaseArrayList;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để khởi chạy hoạt đôộng mới
                Intent intent = new Intent(v.getContext(), Manager.class);

                // Đặt dữ liệu cần thiết vào Intent
                intent.putExtra("item", getItemCount());

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
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove the item from the adapter
                        quan_ly_khu_tro_firebaseArrayList.remove(holder.getAdapterPosition());

                        // Notify the adapter that the data has changed
                        notifyItemRemoved(holder.getAdapterPosition());

                        // Xóa mục khỏi Firebase
                        FirebaseDatabase.getInstance().getReference("Shelter").child("Dv" + countId).removeValue();
                    }
                }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                });

                // Add a negative button to the dialog box

                // Show the dialog box
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return quan_ly_khu_tro_firebaseArrayList.size();
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
    }
}

