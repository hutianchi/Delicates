package com.htc.delicates.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htc.delicates.Activity.WardrobeActivity;
import com.htc.delicates.R;
import com.htc.delicates.MyView.MyViewPager;
import com.htc.delicates.Adapter.ViewPagerAdapter;

public class SelectFragment extends Fragment implements ViewPager.OnPageChangeListener, ViewPager.OnTouchListener{

    MyViewPager viewPager;

    Toolbar mToolbar_select;

    TextView tv_select_normal;

    TextView tv_select_dress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        viewPager = (MyViewPager) view.findViewById(R.id.viewpager_select);
        mToolbar_select = (Toolbar) view.findViewById(R.id.toolbar_select);
        mToolbar_select.inflateMenu(R.menu.menu_wardrobe);
        mToolbar_select.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_wardrobe:
                    Intent intent = new Intent(getActivity(), WardrobeActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        tv_select_normal = (TextView) view.findViewById(R.id.select_normal);
        tv_select_normal.setEnabled(false);
        tv_select_dress = (TextView) view.findViewById(R.id.select_dress);
        tv_select_dress.setEnabled(true);
        tv_select_normal.setOnClickListener( v -> {
            tv_select_normal.setEnabled(false);
            tv_select_dress.setEnabled(true);
            viewPager.setCurrentItem(0);
        });
        tv_select_dress.setOnClickListener( v -> {
            tv_select_dress.setEnabled(false);
            tv_select_normal.setEnabled(true);
            viewPager.setCurrentItem(1);
        });
        init(viewPager);
        viewPager.setScanScroll(false);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new ClothesSelectFragment());
        adapter.addFragment(new DressSelectFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
