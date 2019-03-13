package com.htc.delicates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OrderFragment extends Fragment implements ViewPager.OnPageChangeListener, ViewPager.OnTouchListener{


    ViewPager viewPager;

    Toolbar mToolbar_order;

    TextView tv_order_changfu;

    TextView tv_order_lifu;

    TextView tv_order_yichang;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_order);
        mToolbar_order = (Toolbar) view.findViewById(R.id.toolbar_order);
        mToolbar_order.inflateMenu(R.menu.menu_wardrobe);
        mToolbar_order.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_wardrobe:
                    Intent intent = new Intent(getActivity(), WardrobeActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        tv_order_changfu = (TextView) view.findViewById(R.id.order_changfu);
        tv_order_changfu.setEnabled(false);
        tv_order_lifu = (TextView) view.findViewById(R.id.order_lifu);
        tv_order_lifu.setEnabled(true);
        tv_order_yichang = (TextView) view.findViewById(R.id.order_yichang);
        tv_order_yichang.setEnabled(true);
        tv_order_changfu.setOnClickListener( v -> {
            tv_order_changfu.setEnabled(false);
            tv_order_lifu.setEnabled(true);
            tv_order_yichang.setEnabled(true);
            viewPager.setCurrentItem(0);
        });
        tv_order_lifu.setOnClickListener( v -> {
            tv_order_lifu.setEnabled(false);
            tv_order_changfu.setEnabled(true);
            tv_order_yichang.setEnabled(true);
            viewPager.setCurrentItem(1);
        });
        tv_order_yichang.setOnClickListener( v -> {
            tv_order_yichang.setEnabled(false);
            tv_order_changfu.setEnabled(true);
            tv_order_lifu.setEnabled(true);
            viewPager.setCurrentItem(2);
        });
        init(viewPager);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new ChangfuOrderFragment());
        adapter.addFragment(new ChangfuOrderFragment());
        adapter.addFragment(new ChangfuOrderFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                tv_order_changfu.setEnabled(false);
                tv_order_lifu.setEnabled(true);
                tv_order_yichang.setEnabled(true);
                break;
            case 1:
                tv_order_changfu.setEnabled(true);
                tv_order_lifu.setEnabled(false);
                tv_order_yichang.setEnabled(true);
                break;
            case 2:
                tv_order_changfu.setEnabled(true);
                tv_order_lifu.setEnabled(true);
                tv_order_yichang.setEnabled(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
