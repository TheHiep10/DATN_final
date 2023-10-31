package com.example.afinal.room.member;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.afinal.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailname, detailgender, detaildate, detailaddress, detailstarttime, detailphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailname = findViewById(R.id.infoname);
        detailgender = findViewById(R.id.infogender);
        detaildate = findViewById(R.id.infodate);
        detailaddress = findViewById(R.id.infoaddress);
        detailstarttime = findViewById(R.id.infostarttime);
        detailphone = findViewById(R.id.infophone);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null )
        {
            detailname.setText(bundle.getString("Name"));
            detailgender.setText(bundle.getString("Gender"));
            detaildate.setText(bundle.getString("Date"));
            detailaddress.setText(bundle.getString("Address"));
            detailstarttime.setText(bundle.getString("Starttime"));
            detailphone.setText(bundle.getString("Phone"));
        }

    }
}