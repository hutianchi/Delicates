package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.htc.delicates.R;

public class UserSizeActivity extends AppCompatActivity {

    Toolbar toolbar_user_size;

    EditText userHeight;
    EditText userWeight;
    EditText userXiongwei;
    EditText userYaowei;
    EditText userTunwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_size);

        toolbar_user_size = (Toolbar) findViewById(R.id.toolbar_user_size);
        setSupportActionBar(toolbar_user_size);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_user_size.setNavigationOnClickListener(v -> onBackPressed());

        userHeight = (EditText) findViewById(R.id.user_height);
        userWeight = (EditText) findViewById(R.id.user_wight);
        userXiongwei = (EditText) findViewById(R.id.user_xiongwei);
        userYaowei = (EditText) findViewById(R.id.user_yaowei);
        userTunwei = (EditText) findViewById(R.id.user_yaowei);

    }
}
