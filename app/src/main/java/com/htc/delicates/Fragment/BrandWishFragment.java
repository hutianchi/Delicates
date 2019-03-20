package com.htc.delicates.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.htc.delicates.Activity.AddBrandActivity;
import com.htc.delicates.Adapter.BrandAdapter;
import com.htc.delicates.Model.Brand;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class BrandWishFragment extends Fragment {

    BrandAdapter recommendAdapterBrand;

    RecyclerView brandRecyclerView;

    List<Brand> brandList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_brand, container, false);
        brandRecyclerView = (RecyclerView) view.findViewById(R.id.brand_wish);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        brandRecyclerView.setLayoutManager(layoutManager);
        brandRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        recommendAdapterBrand = new BrandAdapter(getActivity(), brandList, R.layout.wish_brand_item);
        brandRecyclerView.setAdapter(recommendAdapterBrand);
        recommendAdapterBrand.setOnItemClickListener((adapter, view1, position) ->{
            if (position == 0){
                Intent intent = new Intent(getActivity(), AddBrandActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getActivity(), brandList.get(position).brandName, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void init(){
        Brand brand0 = new Brand(0, R.drawable.addbrand, "添加品牌");
        Brand brand1 = new Brand(1, R.drawable.brand1, "GINGKOROOM");
        Brand brand2 = new Brand(2, R.drawable.brand2, "HELLO SEPTEMBER");
        Brand brand3 = new Brand(3, R.drawable.brand3, "devil's bones");
        Brand brand4 = new Brand(4, R.drawable.brand4, "CAYLAR");
        Brand brand5 = new Brand(5, R.drawable.brand5, "FitGlasses");
        Brand brand6 = new Brand(6, R.drawable.brand1, "GINGKOROOM");
        Brand brand7 = new Brand(7, R.drawable.brand2, "HELLO SEPTEMBER");
        Brand brand8 = new Brand(8, R.drawable.brand3, "devil's bones");
        Brand brand9 = new Brand(9, R.drawable.brand4, "CAYLAR");

        brandList.add(brand0);
        brandList.add(brand1);
        brandList.add(brand2);
        brandList.add(brand3);
        brandList.add(brand4);
        brandList.add(brand5);
        brandList.add(brand6);
        brandList.add(brand7);
        brandList.add(brand8);
        brandList.add(brand9);
    }
}
