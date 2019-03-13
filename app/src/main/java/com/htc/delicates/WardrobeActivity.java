package com.htc.delicates;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class WardrobeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ViewPager.OnTouchListener {

    ViewPager viewPager;

    Toolbar mToolbar_wardrobe;

    TextView tv_us;

    TextView tv_wd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        viewPager = (ViewPager) findViewById(R.id.viewpager_wardrobe);
        mToolbar_wardrobe = (Toolbar) findViewById(R.id.toolbar_wardrobe);
        tv_wd = (TextView) findViewById(R.id.tv_wardrobe);
        tv_wd.setEnabled(false);
        tv_us = (TextView) findViewById(R.id.tv_using);
        tv_us.setEnabled(true);
        tv_wd.setOnClickListener(v -> {
            tv_wd.setEnabled(false);
            tv_us.setEnabled(true);
            viewPager.setCurrentItem(0);
        });
        tv_us.setOnClickListener(v -> {
            tv_us.setEnabled(false);
            tv_wd.setEnabled(true);
            viewPager.setCurrentItem(1);
        });
        setSupportActionBar(mToolbar_wardrobe);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar_wardrobe.setNavigationOnClickListener(v -> onBackPressed());
        viewPager.addOnPageChangeListener(this);

        init(viewPager);
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("新闻"));
        adapter.addFragment(BaseFragment.newInstance("图书"));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                tv_us.setEnabled(true);
                tv_wd.setEnabled(false);
                break;
            case 1:
                tv_us.setEnabled(false);
                tv_wd.setEnabled(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
