package com.example.afinal.room;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.example.afinal.room.member.MemberAdapter;
import com.example.afinal.room.member.add_member;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class Manager extends AppCompatActivity {

    //-----------------------------------------------------//
    static final private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    static final private String PHONE_PATTERN = "^[0-9]{10}$";
    private static final int UPDATE_INTERVAL = 15000; // Thời gian cập nhật là 30 giây
    private boolean isUpdating = true;
    TextView headername;
    ImageButton addMemberbtn, btnback;
    RecyclerView recyclerView;
    SearchView searchView;
    public int ID;
    DatabaseReference datamember;
    MemberAdapter memberAdapter;
    private String devicecode;
    private int waitScan, connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager);
        // Init
        init();

        startUpdateThread();

    }

    //-------------------------------------------------------------------//
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
    //--------------------------------------------------------------------//
    //INIT
    private void init(){
        headername = findViewById(R.id.header_name);
        btnback = findViewById(R.id.btnBack_room);
        addMemberbtn = findViewById(R.id.btn_add_member);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        datamember = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.list_member);
        recyclerView.setLayoutManager(new LinearLayoutManager(new Manager()));
    }
    //GET DATA from FragmentHome to Manager
    private void getDataFragmentHome(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null ) {
            devicecode = bundle.getString("DEVICE_CODE");
            headername.setText(bundle.getString("NAME_ROOM"));
            connected = bundle.getInt("CONNECTION");
            Log.d(TAG,"Biến connection = "+connected);
        }
    }
    //GET DATA FROM FIREBASE
    private void getDataFirebase(){
        FirebaseRecyclerOptions<add_member> options =
                new FirebaseRecyclerOptions.Builder<add_member>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("DV").child(devicecode).child("MEMBER"), add_member.class)
                        .build();

        memberAdapter = new MemberAdapter(options, devicecode);
        recyclerView.setAdapter(memberAdapter);
    }
    //SEARCH MEMBER
    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<add_member> options =
                new FirebaseRecyclerOptions.Builder<add_member>()
                        .setQuery( FirebaseDatabase.getInstance().getReference("DV").child(devicecode).child("MEMBER").orderByChild("nameMember").startAt(str).endAt(str+"~"),add_member.class)
                        .build();

        memberAdapter = new MemberAdapter(options, devicecode);
        memberAdapter.startListening();
        recyclerView.setAdapter(memberAdapter);
    }
    //UPLOAD MEMBER TO FIREBASE
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
                    waitScan = 1;
                    Map<String, Object> map = new HashMap<>();
                    map.put("nameMember", name);
                    map.put("genderMember",gender);
                    map.put("dateMember", date);
                    map.put("addressMember", address);
                    map.put("timeMember", time);
                    map.put("phoneMember",phone);
                    datamember= FirebaseDatabase.getInstance().getReference("DV").child(devicecode);
                    //Waiting loading finger
                    datamember.child("waitScan").setValue(waitScan);
                    //Check ID and push ID
                    datamember.child("IDtemp").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Long currentID = snapshot.getValue(Long.class);
                            if (currentID != null)
                            {
                                ID = currentID.intValue();
                                Log.d(TAG, "ID lấy xuống = " + ID);
                                if (ID == 200 || ID < 0 )
                                {
                                    ID = 0;
                                }
                                ID ++;
                                Log.d(TAG, "ID đẩy lên = " + ID);
                                datamember.child("IDtemp").setValue(ID);
                                datamember.child("ID").setValue(ID);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //Turn off dialog add member
                    dialog.dismiss();
                    //Open dialog Loading Finger
                    AlertDialog.Builder builder = new AlertDialog.Builder(Manager.this);
                    builder.setIcon(R.drawable.icon_finger);
                    builder.setTitle("Mời quét vân tay");
                    builder.setView(R.layout.progress_layout);
                    builder.setCancelable(false);
                    AlertDialog dialog1 = builder.create();
                    dialog1.show();
                    //Check WaitScan (if != 1) will close dialog and push infomation member to firebase
                    datamember.child("waitScan").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Long longValue = snapshot.getValue(Long.class);

                            if (longValue != null) {
                                waitScan = longValue.intValue(); // Chuyển đổi từ Long thành int
                                if (waitScan != 1) {
                                    //Push info member
                                    datamember.child("MEMBER").child(String.valueOf(ID))
                                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    dialog1.dismiss();
                                                    Toast.makeText(Manager.this, "Hoàn Thành", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialog1.dismiss();
//                                                    Toast.makeText(Manager.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    dialog1.dismiss();
                                }
                            } else {
                                // Xử lý trường hợp giá trị là null
                                dialog1.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            dialog1.dismiss();
                        }
                    });
                }
            }
        });
        dialog.show();

    }
    //Update data
    private void startUpdateThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isUpdating){
                    //GET DATA FROM FRAGMENT_HOME
                    getDataFragmentHome();
                    //GET DATA FROM FIREBASE TO ADD_MEMBER AND PUSH DATA TO MEMBERADAPTER
                    getDataFirebase();
                    if (connected == 1)
                    {
                        //SEARCH MEMBER
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

                        //Button Back
                        btnback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                            }
                        });

                        //Button ADD MEMBER
                        addMemberbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                uploadData();
                            }
                        });
                    } else {
                        onBackPressed();
                    }

                    try {
                        Thread.sleep(UPDATE_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}