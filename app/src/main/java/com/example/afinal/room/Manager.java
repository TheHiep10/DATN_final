package com.example.afinal.room;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import com.example.afinal.R;
import com.example.afinal.room.member.Adapter;
import com.example.afinal.room.member.add_member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  Manager extends AppCompatActivity {

    //-----------------------------------------------------//
    static final private String USERNAME_PATTERN = "^[a-z A-Z]{0,50}$";
    static final private String PHONE_PATTERN = "^[0-9]{10}$";
    private static final int UPDATE_INTERVAL = 30000; // Thời gian cập nhật là 30 giây
    private Handler handler;
    private TextView headername;
    private ImageButton addMemberbtn, btnback;
    private RecyclerView recyclerView;
    private SearchView searchView;
    public int ID, connected, lock, action, waitScan;
    private DatabaseReference datamember;
    private Adapter adapter;
    private ArrayList<add_member> addMemberArrayList = new ArrayList<>();
    private String devicecode, nameheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager);
        // Init
        init();
        //GET DATA FROM FRAGMENT_HOME
        getDataFragmentHome();

        updateDataFirebase();

    }

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

        // Khởi tạo Handler
        handler = new Handler(Looper.getMainLooper());
    }
    //GET DATA from FragmentHome to Manager
    private void getDataFragmentHome(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null ) {
            devicecode = bundle.getString("DEVICE_CODE");
            nameheader = bundle.getString("NAME_ROOM");
            headername.setText(nameheader);
        }
    }
    //RETRY GET DATA FROM DATABASE
    private void retrieveDataFromFirebase()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DV").child(devicecode);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("HOME").child("SoThanhVien");
        ref.child("connection").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long Connected = snapshot.getValue(Long.class);
                if (Connected != null)
                {
                    connected = Connected.intValue();
                    Log.d(TAG, "Biến connection =" + connected);
                    if (connected != 1)
                    {
                        onBackPressed();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("action").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long actioned = snapshot.getValue(Long.class);
                if (actioned != null)
                {
                    action = actioned.intValue();
                    if (((action == 7) && (connected == 1))) {onBackPressed();}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("lock").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long currentlock = snapshot.getValue(Long.class);
                if (currentlock != null){
                    lock = currentlock.intValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("MEMBER").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addMemberArrayList.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    add_member addMember = childSnapshot.getValue(add_member.class);
                    addMemberArrayList.add(addMember);
                }

                // Cập nhật bộ điều hợp trước khi đặt nó cho RecyclerView
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyFragment", "Error retrieving data from Firebase", error.toException());
            }
        });
    }
    //GET DATA FROM FIREBASE
    private void getDataFirebase(){
        adapter = new Adapter(this, addMemberArrayList, devicecode);
        recyclerView.setAdapter(adapter);
    }
    //SEARCH MEMBER
    private void searchList(String str){
        ArrayList<add_member> searchList = new ArrayList<>();
        for (add_member addMember: addMemberArrayList){
            if (addMember.getNameMember().toLowerCase().contains(str.toLowerCase()))
            {
                searchList.add(addMember);
            }
        }
        adapter.searchDataList(searchList);
    }
    //CHECK ID
    private void checkID(int id){
        for (add_member addMember: addMemberArrayList){
            if (addMember.getIdMember().toString().contains(String.valueOf(id)))
            {
                ID++;
            }
        }
        if (ID == 100 || ID <= 0 )
        {
            ID = 1;
        }
    }
    //UPLOAD MEMBER TO FIREBASEHiển thị danh sách các khu trọ
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
        dialog.show();
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
                    Map<String, Object> map = new HashMap<>();
                    map.put("nameMember", name);
                    map.put("genderMember",gender);
                    map.put("dateMember", date);
                    map.put("addressMember", address);
                    map.put("timeMember", time);
                    map.put("phoneMember",phone);
                    datamember= FirebaseDatabase.getInstance().getReference("DV").child(devicecode);
                    //Set action = 1 is add member
                    datamember.child("action").setValue(1);
                    //Waiting loading finger
                    datamember.child("waitScan").setValue(0);
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
                    //Check ID and push ID
                    datamember.child("IDtemp").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Long currentID = snapshot.getValue(Long.class);
                            if (currentID != null)
                            {
                                ID = currentID.intValue();
                                checkID(ID);
                                Log.d(TAG, "ID được Log ra = " + ID);
                                map.put("idMember", String.valueOf(ID));
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    //Check WaitScan (if != 1) will close dialog and push infomation member to firebase
                    datamember.child("waitScan").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Long currentWait = snapshot.getValue(Long.class);
                            if (currentWait != null) {
                                waitScan = currentWait.intValue();
                                if (waitScan == 1) {
                                    //Push info member
                                    datamember.child("MEMBER").child(String.valueOf(ID)).setValue(map);
                                    Toast.makeText(Manager.this, "Hoàn Thành", Toast.LENGTH_SHORT).show();
                                    map.clear();
                                    dialog1.dismiss();
                                } else if (waitScan == 2)
                                {
                                    map.clear();
                                    Toast.makeText(Manager.this, "Lỗi nhập vân tay", Toast.LENGTH_SHORT).show();
                                    dialog1.dismiss();
                                }
                            } else {
                                map.clear();
                                // Xử lý trường hợp giá trị là null
                                dialog1.dismiss();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            map.clear();
                            dialog1.dismiss();
                        }
                    });
                }
            }
        });

    }
    //Update data
    private void updateDataFirebase(){

        //Get data FROM FIREBASE to arraylist
        retrieveDataFromFirebase();

        //GET DATA FROM ARRAYLIST PUSH DATA TO MEMBERADAPTER
        getDataFirebase();

        //SEARCH MEMBER
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchList(query);
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
                if (lock != 1){
                    uploadData();
                }
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateDataFirebase();
            }
        },UPDATE_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        // Hủy lịch trình khi Activity bị hủy
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}