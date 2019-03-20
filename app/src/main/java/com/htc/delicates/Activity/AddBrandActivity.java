package com.htc.delicates.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.htc.delicates.Adapter.BrandListAdapter;
import com.htc.delicates.Model.Brand;
import com.htc.delicates.MyView.MySidebar;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddBrandActivity extends AppCompatActivity {

    private MySidebar sidebar;

    private BrandListAdapter adapter;

    private List<Brand> brandList = new ArrayList<>();

    private RecyclerView addBrand_list;

    Toolbar mToolbar_addBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand);
        mToolbar_addBrand = (Toolbar) findViewById(R.id.toolbar_addBrand);
        setSupportActionBar(mToolbar_addBrand);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar_addBrand.setNavigationOnClickListener(v -> onBackPressed());
        init();
        sidebar = (MySidebar) findViewById(R.id.addBrand_sidebar);
        sidebar.setOnAlphabetChangeListener(new MySidebar.OnAlphabetChangeListener() {
            @Override
            public void alphabetChangeListener(View v, String alphabet, int section) {
                ((LinearLayoutManager)addBrand_list.getLayoutManager()).scrollToPositionWithOffset(adapter.getPositionForSection(section),0);
            }
        });
        adapter = new BrandListAdapter(brandList, this);
        addBrand_list = (RecyclerView) findViewById(R.id.addBrand_list);
        addBrand_list.setLayoutManager(new LinearLayoutManager(this));
        addBrand_list.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.my_divider));
        addBrand_list.addItemDecoration(divider);
        addBrand_list.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view1, position) ->{

        });
    }

    private void init(){
        Brand brand1 = new Brand(1, R.drawable.brand1, "老王");
        Brand brand2 = new Brand(2, R.drawable.brand2, "老的");
        Brand brand3 = new Brand(3, R.drawable.brand3, "devil's bones");
        Brand brand4 = new Brand(4, R.drawable.brand4, "盲僧");
        Brand brand5 = new Brand(5, R.drawable.brand5, "盲你");
        Brand brand6 = new Brand(6, R.drawable.brand1, "GINGKOROOM");
        Brand brand7 = new Brand(7, R.drawable.brand2, "HELLO SEPTEMBER");
        Brand brand8 = new Brand(8, R.drawable.brand3, "devil's bones");
        Brand brand9 = new Brand(9, R.drawable.brand4, "CAYLAR");

        brandList.add(brand1);
        brandList.add(brand2);
        brandList.add(brand3);
        brandList.add(brand4);
        brandList.add(brand5);
        brandList.add(brand6);
        brandList.add(brand7);
        brandList.add(brand8);
        brandList.add(brand9);
        brandList.add(brand1);
        brandList.add(brand2);
        brandList.add(brand3);
        brandList.add(brand4);
        brandList.add(brand5);
        brandList.add(brand6);
        brandList.add(brand7);
        brandList.add(brand8);
        brandList.add(brand9);
        Collections.sort(brandList);
    }
}
