package com.htc.delicates;

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

public class WishFragment extends Fragment implements ViewPager.OnPageChangeListener, ViewPager.OnTouchListener{

    ViewPager viewPager;

    Toolbar mToolbar_wish;

    TextView tv_wish_clothes;

    TextView tv_wish_brand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_wish);
        mToolbar_wish = (Toolbar) view.findViewById(R.id.toolbar_wish);
        mToolbar_wish.inflateMenu(R.menu.menu_wardrobe);
        mToolbar_wish.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_wardrobe:
                    Intent intent = new Intent(getActivity(), WardrobeActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        tv_wish_clothes = (TextView) view.findViewById(R.id.wish_clothes);
        tv_wish_clothes.setEnabled(false);
        tv_wish_brand = (TextView) view.findViewById(R.id.wish_brand);
        tv_wish_brand.setEnabled(true);
        tv_wish_clothes.setOnClickListener( v -> {
            tv_wish_clothes.setEnabled(false);
            tv_wish_brand.setEnabled(true);
            viewPager.setCurrentItem(0);
        });
        tv_wish_brand.setOnClickListener( v -> {
            tv_wish_brand.setEnabled(false);
            tv_wish_clothes.setEnabled(true);
            viewPager.setCurrentItem(1);
        });
        init(viewPager);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    private void init(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("心愿单品"));
        adapter.addFragment(BaseFragment.newInstance("心愿品牌"));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                tv_wish_clothes.setEnabled(false);
                tv_wish_brand.setEnabled(true);
                break;
            case 1:
                tv_wish_clothes.setEnabled(true);
                tv_wish_brand.setEnabled(false);
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
