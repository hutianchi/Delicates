package com.htc.delicates.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.MyView.CoverFlow;
import com.htc.delicates.MyView.PagerContainer;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class ToBeMemberActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    Toolbar toolbar_member;

    public int[] covers = {R.drawable.month,R.drawable.season,R.drawable.halfyear,R.drawable.year};

    public int[] cardsPrice = {499,1458,2888,5688};

    public int coupon = 0;

    List<Integer> imageList = new ArrayList<>();

    TextView cardPrice;
    TextView couponPrice;
    TextView payWays;
    TextView totalPrice;
    TextView toPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_member);

        toolbar_member = (Toolbar) findViewById(R.id.toolbar_member);
        setSupportActionBar(toolbar_member);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_member.setNavigationOnClickListener(v -> onBackPressed());

        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        ViewPager pager = container.getViewPager();
        pager.setAdapter(new MyPagerAdapter());
        pager.setClipChildren(false);
        pager.addOnPageChangeListener(this);
        //
        pager.setOffscreenPageLimit(15);

        new CoverFlow.Builder().with(pager).pagerMargin(-60).scale(0.3f).spaceSize(0f).build();

        cardPrice = (TextView) findViewById(R.id.member_card_price);
        couponPrice = (TextView) findViewById(R.id.coupon_price);
        payWays = (TextView) findViewById(R.id.pay_ways);
        totalPrice = (TextView) findViewById(R.id.pay_amount);
        toPay = (TextView) findViewById(R.id.to_pay);

        cardPrice.setText("¥"+cardsPrice[0]);
        if (getCoupon()){
            couponPrice.setText(""+coupon);
        }
        totalPrice.setText("¥"+(cardsPrice[0]-coupon));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {}

    @Override
    public void onPageSelected(int i) {
        cardPrice.setText("¥"+cardsPrice[i]);
        if (getCoupon()){
            couponPrice.setText(""+coupon);
        }
        totalPrice.setText("¥"+(cardsPrice[i]-coupon));
    }

    @Override
    public void onPageScrollStateChanged(int i) {}

    public Boolean getCoupon(){
        return false;
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(ToBeMemberActivity.this).inflate(R.layout.membercard_item,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            imageView.setImageDrawable(getResources().getDrawable(covers[position]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return covers.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

}
