package com.example.afinal.room.member;

import static android.provider.Settings.System.getString;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.room.Manager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberAdapter extends FirebaseRecyclerAdapter<add_member, MemberAdapter.myViewHoler> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MemberAdapter(@NonNull FirebaseRecyclerOptions<add_member> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHoler holder, int position, @NonNull add_member model) {
        String gender;
        holder.nameMember.setText(model.getNameMember());
        gender = model.getGenderMember();
        if (gender != null){
            if (gender.equals("Nam"))
            {
                holder.imggender.setImageResource(R.drawable.men);
            } else if (gender.equals("Nữ")){
                holder.imggender.setImageResource(R.drawable.girl);
            }
        }else {
            Toast.makeText(holder.imggender.getContext(),"Lỗi", Toast.LENGTH_SHORT).show();
        }
        holder.starttimeMember.setText(model.getTimeMember());
        holder.phoneMember.setText(model.getPhoneMember());
        holder.txtoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display option menu

                PopupMenu popupMenu = new PopupMenu(holder.txtoption.getContext(), holder.txtoption);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                final Dialog dialog = new Dialog(holder.txtoption.getContext());
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.update_member);

                                EditText fullnamUpdate = dialog.findViewById(R.id.txtnameupdate);
                                RadioGroup genderUpdate = dialog.findViewById(R.id.radiogenderupdate);
                                RadioButton radionam = dialog.findViewById(R.id.namupdate);
                                RadioButton radionu = dialog.findViewById(R.id.nuupdate);
                                EditText dateUpdate = dialog.findViewById(R.id.txtdateupdate);
                                EditText addressUpdate = dialog.findViewById(R.id.txtaddressupdate);
                                EditText phoneUpdate = dialog.findViewById(R.id.txtnumberupdate);
                                EditText timeUpdate = dialog.findViewById(R.id.txttimeupdate);

                                Button accepUpdate = dialog.findViewById(R.id.btn_acceptupdate);

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

                                fullnamUpdate.setText(model.getNameMember());
                                String gender = model.getGenderMember();
                                if (gender.equals("Nam"))
                                {
                                    radionam.setChecked(true);
                                } else if (gender.equals("Nữ")) {
                                    radionu.setChecked(true);
                                }
                                dateUpdate.setText(model.getDateMember());
                                addressUpdate.setText(model.getAddressMember());
                                timeUpdate.setText(model.getTimeMember());
                                phoneUpdate.setText(model.getPhoneMember());

                                dialog.show();
                                accepUpdate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        RadioButton selectGender = dialog.findViewById(genderUpdate.getCheckedRadioButtonId());
                                        String gender = selectGender.getText().toString();
                                        int var = holder.getAdapterPosition();

                                        Map<String,Object> map = new HashMap<>();
                                        map.put("nameMember", fullnamUpdate.getText().toString());
                                        map.put("genderMember",gender);
                                        map.put("dateMember", dateUpdate.getText().toString());
                                        map.put("addressMember", addressUpdate.getText().toString());
                                        map.put("timeMember", timeUpdate.getText().toString());
                                        map.put("phoneMember",phoneUpdate.getText().toString());


                                        FirebaseDatabase.getInstance().getReference("Dv1").child("MEMBER").child(getRef(var).getKey()).updateChildren(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(holder.nameMember.getContext(),"Chỉnh sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(holder.nameMember.getContext(),"Chỉnh sửa thông tin không thành công", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                });

                                    }
                                });
                                break;
                            case R.id.menu_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nameMember.getContext());
                                builder.setTitle("Bạn có chắc chắn muốn xóa?");
                                builder.setMessage("Thông tin thành viên không thể khôi phục");
                                int var = holder.getAdapterPosition();

                                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference("Dv1").child("MEMBER").child(getRef(var).getKey()).removeValue();
                                    }
                                });

                                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.recCard.getContext(), DetailActivity.class);
                intent.putExtra("Name", model.getNameMember());
                intent.putExtra("Gender", model.getGenderMember());
                intent.putExtra("Date", model.getDateMember());
                intent.putExtra("Address", model.getAddressMember());
                intent.putExtra("Starttime", model.getTimeMember());
                intent.putExtra("Phone", model.getPhoneMember());
                holder.recCard.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infomation_member, parent,false);
        return new myViewHoler(view);
    }

    class myViewHoler extends RecyclerView.ViewHolder{

        ImageView imggender;
        TextView nameMember, starttimeMember, phoneMember, txtoption;
        CardView recCard;

        public myViewHoler(@NonNull View itemView) {
            super(itemView);

            imggender = itemView.findViewById(R.id.sex);
            recCard = itemView.findViewById(R.id.recCard);
            nameMember = itemView.findViewById(R.id.name_member);
            starttimeMember = itemView.findViewById(R.id.starttime);
            phoneMember = itemView.findViewById(R.id.textphone);
            txtoption = itemView.findViewById(R.id.txtOption);
        }
    }

}
