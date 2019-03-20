package com.htc.delicates.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Fragment.BaseFragment;
import com.htc.delicates.R;
import com.htc.delicates.Adapter.ViewPagerAdapter;

public class WardrobeActivity extends AppCompatActivity {


    Toolbar mToolbar_wardrobe;

    TextView sendClothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        mToolbar_wardrobe = (Toolbar) findViewById(R.id.toolbar_wardrobe);
        setSupportActionBar(mToolbar_wardrobe);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar_wardrobe.setNavigationOnClickListener(v -> onBackPressed());
        sendClothes = (TextView) findViewById(R.id.tv_send);
        sendClothes.setOnClickListener(v -> {
            Toast.makeText(this, "寄送成功",Toast.LENGTH_SHORT).show();
        });

    }


}
