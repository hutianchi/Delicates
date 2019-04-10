package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.htc.delicates.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar_settings = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar_settings);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_settings.setNavigationOnClickListener(v -> onBackPressed());

        TextView introduction = (TextView) findViewById(R.id.introduction);
        introduction.setOnClickListener(this);
        TextView clearCache = (TextView) findViewById(R.id.clear_cache);
        clearCache.setOnClickListener(this);
        TextView clearWish = (TextView) findViewById(R.id.clear_wish);
        clearWish.setOnClickListener(this);
        TextView logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.introduction:

                break;
            case R.id.clear_cache:

                break;
            case R.id.clear_wish:

                break;
            case R.id.logout:

                break;
        }
    }
}
