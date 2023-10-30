package com.example.afinal.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.afinal.R;
import com.example.afinal.room.member.MemberAdapter;
import com.example.afinal.room.member.add_member;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Manager extends AppCompatActivity {

    //-----------------------------------------------------//
    static final private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    static final private String PHONE_PATTERN = "^[0-9]{10}$";
    ImageButton addMemberbtn;
    RecyclerView recyclerView;
    SearchView searchView;
    public int ID = 1;
    DatabaseReference datamember;
    MemberAdapter memberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager);
        // Init
        addMemberbtn = findViewById(R.id.btn_add_member);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        datamember = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.list_member);
        recyclerView.setLayoutManager(new LinearLayoutManager(new Manager()));
        //Firebase
        FirebaseRecyclerOptions<add_member> options =
                new FirebaseRecyclerOptions.Builder<add_member>()
                        .setQuery( FirebaseDatabase.getInstance().getReference("Dv1").child("MEMBER"),add_member.class)
                        .build();

        memberAdapter = new MemberAdapter(options);
        recyclerView.setAdapter(memberAdapter);
        //Search member
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        addMemberbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        memberAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        memberAdapter.stopListening();
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<add_member> options =
                new FirebaseRecyclerOptions.Builder<add_member>()
                        .setQuery( FirebaseDatabase.getInstance().getReference("Dv1").child("MEMBER").orderByChild("nameMember").startAt(str).endAt(str+"~"),add_member.class)
                        .build();

        memberAdapter = new MemberAdapter(options);
        memberAdapter.startListening();
        recyclerView.setAdapter(memberAdapter);
    }

    public void uploadData() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_member);

        EditText fullnameMember = dialog.findViewById(R.id.txtname);
        RadioGroup genderMember = dialog.findViewById(R.id.radiosex);
        EditText dateMember = dialog.findViewById(R.id.txtdate);
        EditText addressMember = dialog.findViewById(R.id.txtaddress);
        EditText phoneMember = dialog.findViewById(R.id.txtnumber);
        EditText timeMember = dialog.findViewById(R.id.txtdatestart);
        Button btnaccept = dialog.findViewById(R.id.btn_accept);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        if (Gravity.CENTER == Gravity.CENTER) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullnameMember.setError(null);
                phoneMember.setError(null);
                addressMember.setError(null);

                RadioButton selectGender = dialog.findViewById(genderMember.getCheckedRadioButtonId());
                String name = fullnameMember.getText().toString().trim();
                String date = dateMember.getText().toString();
                String address = addressMember.getText().toString().trim();
                String phone = phoneMember.getText().toString().trim();
                String time = timeMember.getText().toString();
                String gender = selectGender.getText().toString();
                boolean cancel = true;
                //check name
                if (name.matches("")){
                    fullnameMember.setError(getString(R.string.error_field_username_empty));
                    cancel = false;
                } else if (!name.matches(USERNAME_PATTERN)) {
                    fullnameMember.setError(getString(R.string.error_field_username_required));
                }
                //check gender
                if (genderMember.getCheckedRadioButtonId() == -1){
                    Toast.makeText(new Manager(),"Chọn giới tính",Toast.LENGTH_LONG).show();
                    cancel = false;
                }
                //check address
                if (address.matches("")){
                    addressMember.setError(getString(R.string.error_field_nativecountry_empty));
                }
                //check number
                if (phone.matches("")) {
                    phoneMember.setError(getString(R.string.error_field_phone_empty));
                    cancel = false;
                } else if (!phone.matches(PHONE_PATTERN)) {
                    phoneMember.setError(getString(R.string.error_field_phone_required));
                    cancel = false;
                }
                if (cancel) {
//                    add_member dataClass = new add_member(name, gender, date, address, time, phone);
                    Map<String, Object> map = new HashMap<>();
                    map.put("nameMember", name);
                    map.put("genderMember",gender);
                    map.put("dateMember", date);
                    map.put("addressMember", address);
                    map.put("timeMember", time);
                    map.put("phoneMember",phone);
                    datamember = FirebaseDatabase.getInstance().getReference("Dv1");
                    datamember.child("MEMBER").child(String.valueOf(ID))
                            .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        ID++;
                                        dialog.dismiss();
                                        Toast.makeText(Manager.this, "Hoàn Thành", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialog.dismiss();
                                    Toast.makeText(Manager.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        dialog.show();

    }
}