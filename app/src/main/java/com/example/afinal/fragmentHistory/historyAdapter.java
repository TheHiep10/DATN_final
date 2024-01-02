package com.example.afinal.fragmentHistory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class historyAdapter extends FirebaseRecyclerAdapter<lich_su_ra_vao, historyAdapter.myviewHolder> {
    private String devicecode, iD;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public historyAdapter(@NonNull FirebaseRecyclerOptions<lich_su_ra_vao> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull lich_su_ra_vao model) {
        holder.dayHistory.setText(model.getDate());
        holder.timeHistory.setText(model.getTime());
        holder.imgInOut.setImageResource(R.drawable.login);
        devicecode = model.getKey();
        iD = String.valueOf(model.getUser_id());
        Log.d("FirebasePath", "Device code: " + devicecode + ", ID: " + iD);
        if (devicecode != null && iD != null)
        {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DV").child(devicecode);
            ref.child("MEMBER").child(iD).child("nameMember").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String foundName = snapshot.getValue(String.class);
                        holder.nameMember.setText(foundName);
                    } else {
                        if(model.getTime() != null)
                        {
                            DatabaseReference data = FirebaseDatabase.getInstance().getReference("listUsedId").child(model.getTime());
                            data.removeValue();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            ref.child("tenKhuTro").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String nameKhuTro = snapshot.getValue(String.class);
                        holder.txtKhutro.setText(nameKhuTro);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_history, parent, false);
        return new myviewHolder(view);
    }


    public class myviewHolder extends RecyclerView.ViewHolder {

        ImageView imgInOut;
        TextView nameMember, timeHistory, dayHistory, txtKhutro;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            imgInOut = itemView.findViewById(R.id.imageHistory);
            nameMember = itemView.findViewById(R.id.nameMember_history);
            timeHistory = itemView.findViewById(R.id.timeHistory);
            dayHistory = itemView.findViewById(R.id.dayHistory);
            txtKhutro = itemView.findViewById(R.id.txtDeviceHistory);
        }
    }
}

