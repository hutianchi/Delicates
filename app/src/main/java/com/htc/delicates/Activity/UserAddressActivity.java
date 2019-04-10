package com.htc.delicates.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.htc.delicates.Adapter.AddressAdapter;
import com.htc.delicates.Model.Address;
import com.htc.delicates.MyView.SlideRecyclerView;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class UserAddressActivity extends AppCompatActivity {

    Toolbar toolbar_address;

    AddressAdapter addressAdapter;

    SlideRecyclerView recyclerView;

    List<Address> mList = new ArrayList<>();

    TextView addAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);

        toolbar_address = (Toolbar) findViewById(R.id.toolbar_user_address);
        setSupportActionBar(toolbar_address);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_address.setNavigationOnClickListener(v -> onBackPressed());

        addAddress = (TextView) findViewById(R.id.add_address);
        addAddress.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddressEditActivity.class);
            startActivity(intent);
        });

        recyclerView = (SlideRecyclerView) findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        addressAdapter = new AddressAdapter(this, mList);
        recyclerView.setAdapter(addressAdapter);
        addressAdapter.setOnDeleteClickListener((view1, position) -> {
            mList.remove(position);
            addressAdapter.notifyDataSetChanged();
            recyclerView.closeMenu();
        });
        addressAdapter.setOnEditClickListener((view, position) -> {
            Intent intent = new Intent(this, AddressEditActivity.class);
            startActivity(intent);
        });
    }


    private void init(){
        Address address1 = new Address("胡爸爸","130xxxxxxxx","湖北省武汉市洪山区华中科技大学韵苑xx栋");
        Address address2 = new Address("胡爸爸","130xxxxxxxx","湖北省武汉市洪山区华中科技大学韵苑xx栋湖北省武汉市洪山区华中科技大学韵苑xx栋");
        mList.add(address1);
        mList.add(address2);
    }
}
