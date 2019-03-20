package com.htc.delicates.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.htc.delicates.Fragment.OrderFragment;
import com.htc.delicates.R;
import com.htc.delicates.Fragment.RecommendFragment;
import com.htc.delicates.Fragment.SelectFragment;
import com.htc.delicates.Fragment.UserFragment;
import com.htc.delicates.MyView.MyViewPager;
import com.htc.delicates.Adapter.ViewPagerAdapter;
import com.htc.delicates.Fragment.WishFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, ViewPager.OnTouchListener {

    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;

    MyViewPager viewPager;

    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        viewPager = (MyViewPager) findViewById(R.id.viewpager_main);
        viewPager.setScanScroll(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);

        init(viewPager);
        //StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        //StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new RecommendFragment());
        adapter.addFragment(new SelectFragment());
        adapter.addFragment(new WishFragment());
        adapter.addFragment(new OrderFragment());
        adapter.addFragment(new UserFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.item_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.item_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.item_4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.item_5:
                viewPager.setCurrentItem(4);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        if (menuItem != null){
            menuItem.setChecked(false);
        }else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        menuItem = bottomNavigationView.getMenu().getItem(i);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}
