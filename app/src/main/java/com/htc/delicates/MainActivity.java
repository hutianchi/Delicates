package com.htc.delicates;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, ViewPager.OnTouchListener {

    BottomNavigationView bottomNavigationView;

    ViewPager viewPager;

    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_wardrobe:
                        Intent intent = new Intent(MainActivity.this, WardrobeActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);

        init(viewPager);
        //StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        //StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("新闻"));
        adapter.addFragment(BaseFragment.newInstance("图书"));
        adapter.addFragment(BaseFragment.newInstance("发现"));
        adapter.addFragment(BaseFragment.newInstance("更多"));
        adapter.addFragment(BaseFragment.newInstance("个人中心"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_firstpage_wardrobe, menu);
        return true;
    }
}
