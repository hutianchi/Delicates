package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.htc.delicates.R;

public class MyWalletActivity extends AppCompatActivity {

    Toolbar toolbar_Wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

        toolbar_Wallet = (Toolbar) findViewById(R.id.toolbar_wallet);
        setSupportActionBar(toolbar_Wallet);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_Wallet.setNavigationOnClickListener(v -> onBackPressed());
    }
}
