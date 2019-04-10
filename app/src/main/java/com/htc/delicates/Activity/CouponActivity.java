package com.htc.delicates.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.htc.delicates.Adapter.CouponAdapter;
import com.htc.delicates.Model.Coupon;
import com.htc.delicates.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponActivity extends AppCompatActivity {

    Toolbar toolbar_coupon;

    RecyclerView couponRecycler;

    CouponAdapter adapter;

    List<Coupon> couponList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        toolbar_coupon = (Toolbar) findViewById(R.id.toolbar_coupon);
        setSupportActionBar(toolbar_coupon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_coupon.setNavigationOnClickListener(v -> onBackPressed());

        couponRecycler = (RecyclerView) findViewById(R.id.coupon_list);
        couponRecycler.setLayoutManager(new LinearLayoutManager(this));
        couponRecycler.setItemAnimator(new DefaultItemAnimator());
        init();
        adapter = new CouponAdapter(this, couponList);
        couponRecycler.setAdapter(adapter);

        adapter.setOnUseClickListener((view1, position) -> {
            //使用不同种类
        });


    }




    private void init(){
        Coupon coupon1 = new Coupon(20, new Date(System.currentTimeMillis()),"月卡专用");
        Coupon coupon2 = new Coupon(30, new Date(System.currentTimeMillis()),"季卡专用");
        Coupon coupon3 = new Coupon(50, new Date(System.currentTimeMillis()),"年卡专用");

        couponList.add(coupon1);
        couponList.add(coupon2);
        couponList.add(coupon3);
    }
}
