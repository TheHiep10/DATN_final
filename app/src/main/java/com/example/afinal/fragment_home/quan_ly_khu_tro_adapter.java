package com.example.afinal.fragment_home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.afinal.MyApp.CHANNEL_ID;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.MyApp;
import com.example.afinal.R;
import com.example.afinal.room.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class quan_ly_khu_tro_adapter extends RecyclerView.Adapter<MyViewHolder> {
    DatabaseReference firebaseHome;
    private Fragment context;
    private ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList;
    public int countId, countMember;
    private int connected, action;
    private String device;
    public quan_ly_khu_tro_adapter(Fragment context, ArrayList<quan_ly_khu_tro_firebase> quan_ly_khu_tro_firebaseArrayList, int action, int countId, int countMember) {
        this.context = context;
        this.quan_ly_khu_tro_firebaseArrayList = quan_ly_khu_tro_firebaseArrayList;
        this.action = action;
        this.countId = countId;
        this.countMember = countMember;
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
        action = quanLyKhuTroFirebase.getAction();
        connected = quanLyKhuTroFirebase.getConnection();
        if ((action < 7) && (connected == 1) ) {
            holder.imgStatus.setImageResource(R.drawable.icon_connected);
            holder.status.setText("Đã kết nối");
            Log.e(TAG, "So Thanh Vien khi gui = " + countMember);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để khởi chạy hoạt động mới
                    Intent intent = new Intent(v.getContext(), Manager.class);

                    // Đặt dữ liệu cần thiết vào Intent
                    intent.putExtra("DEVICE_CODE", quanLyKhuTroFirebase.getMaThietBi());
                    intent.putExtra("NAME_ROOM", quanLyKhuTroFirebase.getTenKhuTro());
                    // Khởi chạy hoạt động mới
                    v.getContext().startActivities(new Intent[]{intent});
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.inflate(R.menu.optionmenu_home);
                    device = quanLyKhuTroFirebase.getMaThietBi();
                    firebaseHome = FirebaseDatabase.getInstance().getReference("DV").child(device);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_pass:
                                    final Dialog dialog = new Dialog(v.getContext());
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.changepasss_dialog);

                                    EditText edtDivice = dialog.findViewById(R.id.edtdevice);
                                    EditText edtPassword = dialog.findViewById(R.id.edtchangePass);
                                    Button btnaccept = dialog.findViewById(R.id.btn_acceptchange);

                                    Window window = dialog.getWindow();
                                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    WindowManager.LayoutParams windowAttributes = window.getAttributes();
                                    windowAttributes.gravity = Gravity.BOTTOM;
                                    window.setAttributes(windowAttributes);
                                    if (Gravity.BOTTOM == Gravity.BOTTOM) {
                                        dialog.setCancelable(true);
                                    } else {
                                        dialog.setCancelable(false);
                                    }

                                    edtDivice.setText(device);
                                    edtDivice.setEnabled(false);
                                    firebaseHome.child("Key").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists())
                                            {
                                                Object object = snapshot.getValue(Object.class);
                                                if (object != null){
                                                    edtPassword.setText(object.toString());
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    dialog.show();

                                    btnaccept.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            firebaseHome.child("action").setValue(6);
                                            String pass = edtPassword.getText().toString();
                                            firebaseHome.child("Key").setValue(pass);
                                            Toast.makeText(v.getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                                    break;
                                // Change password Wifi
                                case R.id.menu_wifi:
                                    final Dialog dialog1 = new Dialog(v.getContext());
                                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog1.setContentView(R.layout.wifichange_dialog);

                                    EditText edtWifiDivice = dialog1.findViewById(R.id.edtWifiDevic);
                                    EditText edtUser = dialog1.findViewById(R.id.edtUserWifi);
                                    EditText edtpass = dialog1.findViewById(R.id.edtPassWifi);
                                    Button btnacceptWifi = dialog1.findViewById(R.id.btn_acceptWifi);

                                    Window window1 = dialog1.getWindow();
                                    window1.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                    window1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    WindowManager.LayoutParams windowAttributes1 = window1.getAttributes();
                                    windowAttributes1.gravity = Gravity.BOTTOM;
                                    window1.setAttributes(windowAttributes1);
                                    if (Gravity.BOTTOM == Gravity.BOTTOM) {
                                        dialog1.setCancelable(true);
                                    } else {
                                        dialog1.setCancelable(false);
                                    }
                                    edtWifiDivice.setText(device);
                                    edtWifiDivice.setEnabled(false);
                                    firebaseHome.child("SSID").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            edtUser.setText(snapshot.getValue().toString());
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    firebaseHome.child("PASS").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            edtpass.setText(snapshot.getValue().toString());
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    dialog1.show();
                                    btnacceptWifi.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            firebaseHome.child("action").setValue(5);
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("SSID", edtUser.getText().toString());
                                            map.put("PASS",edtpass.getText().toString());
                                            firebaseHome.updateChildren(map);
                                            Toast.makeText(v.getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                            dialog1.dismiss();
                                        }
                                    });
                                    break;
                                //Delete item
                                case R.id.menu_delete:
                                    // Create a dialog builder
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                                    // Set the title and message of the dialog box
                                    builder.setIcon(R.drawable.icon_delete);
                                    builder.setTitle("Bạn muốn xóa khu trọ?");
                                    builder.setMessage("Thông tin không thể khôi phục");

                                    // Add a positive button to the dialog box
                                    builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Gửi hành động "8" lên Firebase
                                            firebaseHome.child("action").setValue(8);
                                            // Thực hiện các hành động khác sau khi xóa
                                            countId--;
                                            if (countId <= 0) {
                                                countId = 0;
                                            }
                                            Log.d(TAG, "gia trị countid sau khi giảm" + countId);
                                            FirebaseDatabase.getInstance().getReference().child("HOME/SoKhuTro").setValue(countId);
                                            countMember = countMember - quanLyKhuTroFirebase.getCountMember();
                                            FirebaseDatabase.getInstance().getReference().child("HOME/SoThanhVien").setValue(countMember);

                                            // Hiển thị dialog xác nhận xóa khu trọ
                                            AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(v.getContext());

                                            // Set the title and message of the confirmation dialog box
                                            confirmBuilder.setIcon(R.drawable.icon_delete);
                                            confirmBuilder.setTitle("Đang xóa tất cả dữ liệu");
                                            confirmBuilder.setView(R.layout.progress_layout);

                                            // Add a positive button to the confirmation dialog box
                                            confirmBuilder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Xác nhận xóa khu trọ
                                                    firebaseHome.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {

                                                            } else {
                                                                // Xử lý khi có lỗi xảy ra trong quá trình xóa
                                                                Toast.makeText(holder.itemView.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                            // Set cancelable to false to prevent dialog from closing when tapping outside
                                            confirmBuilder.setCancelable(false);

                                            // Show the confirmation dialog box after a delay
                                            v.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    confirmBuilder.show();
                                                }
                                            }, 4725); // Độ trễ: 1000 milliseconds (6 giây)
                                        }
                                    });
                                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.setCancelable(false);
                                    // Show the dialog box
                                    builder.show();
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });
            if (quanLyKhuTroFirebase.getLock() == 1){
                Log.e(TAG,"Gọi thông báo");
                sendTempNotification(quanLyKhuTroFirebase.getMaThietBi());
            }
        } else {
            holder.imgStatus.setImageResource(R.drawable.icon_disconnected);
            holder.status.setText("Chưa kết nối");
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.inflate(R.menu.optionmenu_home);
                    device = quanLyKhuTroFirebase.getMaThietBi();
                    firebaseHome = FirebaseDatabase.getInstance().getReference("DV").child(device);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.menu_delete:
                                    firebaseHome.child("Key").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Object object = snapshot.getValue(Object.class);
                                            if (object == null){
                                                // Create a dialog builder
                                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                                                // Set the title and message of the dialog box
                                                builder.setIcon(R.drawable.icon_delete);
                                                builder.setTitle("Bạn muốn xóa khu trọ?");
                                                builder.setMessage("Thông tin không thể khôi phục");

                                                // Add a positive button to the dialog box
                                                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // Thực hiện các hành động khác sau khi xóa
                                                        countId--;
                                                        if (countId <= 0) {
                                                            countId = 0;
                                                        }
                                                        Log.d(TAG, "gia trị countid sau khi giảm" + countId);
                                                        FirebaseDatabase.getInstance().getReference().child("HOME/SoKhuTro").setValue(countId);
                                                        firebaseHome.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {

                                                                } else {
                                                                    // Xử lý khi có lỗi xảy ra trong quá trình xóa
                                                                    Toast.makeText(holder.itemView.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                builder.show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return quan_ly_khu_tro_firebaseArrayList.size();
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }
    public void  setCountMember( int countMember) {
        this.countMember = countMember;
    }
    private void sendTempNotification(String code) {
        // Tạo kênh thông báo
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        RemoteViews notificationLayout = new RemoteViews(context.getContext().getPackageName(), R.layout.custom_notification_layout);

        notificationLayout.setTextViewText(R.id.title, "CẢNH BÁO BẢO MẬT");
        notificationLayout.setTextViewText(R.id.info, "Cửa khóa thiết bị " + code + " bị vô hiệu hóa");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = simpleDateFormat.format(new Date());
        notificationLayout.setTextViewText(R.id.time, strDate);

        Notification notification = new NotificationCompat.Builder(context.getContext(), MyApp.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_small)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCustomContentView(notificationLayout)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context.getContext());
        notificationManagerCompat.notify(getNotificationId(), notification);
    }
    private int getNotificationId() {
        return (int) new Date().getTime();
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

