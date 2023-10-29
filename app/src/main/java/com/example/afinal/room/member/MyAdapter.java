package com.example.afinal.room.member;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<add_member> dataList;

    public MyAdapter(Context context, List<add_member> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infomation_member, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String gender;
        holder.nameMember.setText(dataList.get(position).getName_member());
        gender = dataList.get(position).getGender_member();
        if (gender.equals("Nam"))
        {
            holder.imggender.setImageResource(R.drawable.men);
        } else if (gender.equals("Nữ")){
            holder.imggender.setImageResource(R.drawable.girl);
        }
        holder.starttimeMember.setText(dataList.get(position).getStarttime_member());
        holder.phoneMember.setText(dataList.get(position).getPhone_member());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getName_member());
                intent.putExtra("Gender", dataList.get(holder.getAdapterPosition()).getGender_member());
                intent.putExtra("Date", dataList.get(holder.getAdapterPosition()).getDate_Member());
                intent.putExtra("Address", dataList.get(holder.getAdapterPosition()).getAddress_Member());
                intent.putExtra("Starttime", dataList.get(holder.getAdapterPosition()).getStarttime_member());
                intent.putExtra("Phone", dataList.get(holder.getAdapterPosition()).getPhone_member());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imggender;
    TextView nameMember, starttimeMember, phoneMember;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imggender = itemView.findViewById(R.id.sex);
        recCard = itemView.findViewById(R.id.recCard);
        nameMember = itemView.findViewById(R.id.name_member);
        starttimeMember = itemView.findViewById(R.id.starttime);
        phoneMember = itemView.findViewById(R.id.textphone);

    }
}