package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htc.delicates.Model.Address;
import com.htc.delicates.Model.Order;
import com.htc.delicates.R;

import java.text.DateFormat;
import java.util.Date;

public class OrderDetailActivity extends AppCompatActivity {

    Toolbar toolbar_order_detail;

    TextView orderState;
    TextView payment;

    LinearLayout order1;
    LinearLayout order2;
    LinearLayout order3;

    ImageView order1Image;
    TextView order1Text;
    ImageView order2Image;
    TextView order2Text;
    ImageView order3Image;
    TextView order3Text;
    View split3;
    View split4;

    TextView addressName;
    TextView addressPhone;
    TextView addressDetail;

    TextView orderId;
    TextView orderClothesCount;
    TextView orderCreateTime;
    TextView orderBackTime;

    Order order1Item = new Order(R.drawable.test1,"test1");
    Order order2Item = new Order(R.drawable.test2,"test2");
    Order order3Item = new Order(R.drawable.test3,"test3");

    Address address = new Address("胡爸爸","130xxxxxxxx","湖北省武汉市洪山区华中科技大学韵苑xx栋");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        toolbar_order_detail = (Toolbar) findViewById(R.id.toolbar_order_detail);
        setSupportActionBar(toolbar_order_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_order_detail.setNavigationOnClickListener(v -> onBackPressed());

        orderState = (TextView) findViewById(R.id.order_state);
        payment = (TextView) findViewById(R.id.order_payment);

        order1 = (LinearLayout) findViewById(R.id.order1);
        order2 = (LinearLayout) findViewById(R.id.order2);
        order3 = (LinearLayout) findViewById(R.id.order3);

        order1Image = (ImageView) findViewById(R.id.order1_image);
        order1Text = (TextView) findViewById(R.id.order1_text);
        order2Image = (ImageView) findViewById(R.id.order2_image);
        order2Text = (TextView) findViewById(R.id.order2_text);
        order3Image = (ImageView) findViewById(R.id.order3_image);
        order3Text = (TextView) findViewById(R.id.order3_text);
        split3 = (View) findViewById(R.id.order_split3);
        split4 = (View) findViewById(R.id.order_split4);

        order1Image.setImageResource(order1Item.imageId);
        order1Text.setText(order1Item.orderName);
        order2Image.setImageResource(order2Item.imageId);
        order2Text.setText(order2Item.orderName);
        order3Image.setImageResource(order3Item.imageId);
        order3Text.setText(order3Item.orderName);

        order3.setVisibility(View.GONE);
        split4.setVisibility(View.GONE);


        addressName = (TextView) findViewById(R.id.order_address_name);
        addressPhone = (TextView) findViewById(R.id.order_address_phone);
        addressDetail = (TextView) findViewById(R.id.order_address_details);

        addressName.setText(address.userName);
        addressPhone.setText(address.phoneNumber);
        addressDetail.setText(address.address);

        orderId = (TextView) findViewById(R.id.order_id);
        orderClothesCount = (TextView) findViewById(R.id.order_cnt);
        orderCreateTime = (TextView) findViewById(R.id.order_create);
        orderBackTime = (TextView) findViewById(R.id.order_back);

        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        orderCreateTime.setText(dateFormat.format(date));
        orderBackTime.setText(dateFormat.format(date));
    }

}
