package com.example.afinal.fragmentHistory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.fragment_home.WrapContentLinearLayoutManager;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragmentHistory extends Fragment {

    RecyclerView recyclerView;
    historyAdapter historyAdapter;
    private ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_lich_su, container, false);
        imageView = mView.findViewById(R.id.imageHistory);
        recyclerView = mView.findViewById(R.id.listHistory);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        FirebaseRecyclerOptions<lich_su_ra_vao> options =
                new FirebaseRecyclerOptions.Builder<lich_su_ra_vao>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("listUsedId"), lich_su_ra_vao.class)
                        .build();
        historyAdapter = new historyAdapter(options);
        recyclerView.setAdapter(historyAdapter);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setIcon(R.drawable.icon_delete);
                builder.setTitle("Bạn muốn xóa toàn bộ lich sử?");
                builder.setMessage("Thông tin không thể khôi phục");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("listUsedId");
                        ref.removeValue();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        historyAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        historyAdapter.stopListening();
    }

}