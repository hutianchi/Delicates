package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.htc.delicates.R;

public class AddressEditActivity extends AppCompatActivity {

    Toolbar toolbar_edit_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);

        toolbar_edit_address = (Toolbar) findViewById(R.id.toolbar_edit_address);
        setSupportActionBar(toolbar_edit_address);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_edit_address.setNavigationOnClickListener(v -> onBackPressed());
    }
}
