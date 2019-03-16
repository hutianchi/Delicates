package com.htc.delicates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Model.Brand;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.Util.RecommedUpdateAdapter;
import com.htc.delicates.Util.RecommendAdapter;
import com.htc.delicates.Util.RecommendBrandAdapter;
import com.htc.delicates.Util.RecyclerCoverFlow;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment implements RecommedUpdateAdapter.onItemClick{

    RecommedUpdateAdapter recommendAdapterUpdate;
    RecommendAdapter recommendAdapterPersonal;
    RecommendBrandAdapter recommendAdapterBrand;
    RecommendAdapter recommendAdapterRandom;

    RecyclerCoverFlow recyclerCoverFlow_recommend_update;
    RecyclerView recyclerView_recommend_personal;
    RecyclerView recyclerView_recommend_brand;
    RecyclerView recyclerView_recommend_random;

    List<Clothes> clothesListPersonal = new ArrayList<>();
    List<Clothes> clothesListUpdate = new ArrayList<>();
    List<Brand> brandList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_recommend);
        mToolbar.inflateMenu(R.menu.menu_wardrobe);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_wardrobe:
                    Intent intent = new Intent(getActivity(), WardrobeActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });


        init();

        recyclerCoverFlow_recommend_update = (RecyclerCoverFlow) view.findViewById(R.id.recommend_update);
        recommendAdapterUpdate = new RecommedUpdateAdapter(getActivity(), this, clothesListUpdate);
        recyclerCoverFlow_recommend_update.setAdapter(recommendAdapterUpdate);
        recyclerCoverFlow_recommend_update.scrollToPosition(2);
        recyclerCoverFlow_recommend_update.setOnItemSelectedListener(position -> {

            if (position<=1){
                recyclerCoverFlow_recommend_update.scrollToPosition(position+clothesListUpdate.size()-4);
            }else if (position>=(clothesListUpdate.size()-2)){
                recyclerCoverFlow_recommend_update.scrollToPosition(position-(clothesListUpdate.size()-4));
            }
        });


        recyclerView_recommend_personal = (RecyclerView) view.findViewById(R.id.recyclerView_recommend_personal);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 3);
        recyclerView_recommend_personal.setLayoutManager(layoutManager1);
        recyclerView_recommend_personal.setItemAnimator(new DefaultItemAnimator());
        recommendAdapterPersonal = new RecommendAdapter(getActivity(), clothesListPersonal);
        recyclerView_recommend_personal.setAdapter(recommendAdapterPersonal);
        recommendAdapterPersonal.setOnItemClickListener((adapter, view1, position) ->{
            Toast.makeText(getActivity(), clothesListPersonal.get(position).clothesId+"111111111111111",Toast.LENGTH_SHORT).show();
        });

        recyclerView_recommend_brand = (RecyclerView) view.findViewById(R.id.recyclerView_recommend_brand);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 3);
        recyclerView_recommend_brand.setLayoutManager(layoutManager2);
        recyclerView_recommend_brand.setItemAnimator(new DefaultItemAnimator());
        recommendAdapterBrand = new RecommendBrandAdapter(getActivity(), brandList);
        recyclerView_recommend_brand.setAdapter(recommendAdapterBrand);
        recommendAdapterBrand.setOnItemClickListener((adapter, view1, position) ->{
            Toast.makeText(getActivity(), brandList.get(position).brandName, Toast.LENGTH_SHORT).show();
        });

        recyclerView_recommend_random = (RecyclerView) view.findViewById(R.id.recyclerView_recommend_random);
        GridLayoutManager layoutManager3 = new GridLayoutManager(getActivity(), 3);
        recyclerView_recommend_random.setLayoutManager(layoutManager3);
        recyclerView_recommend_random.setItemAnimator(new DefaultItemAnimator());
        recommendAdapterRandom = new RecommendAdapter(getActivity(), clothesListPersonal);
        recyclerView_recommend_random.setAdapter(recommendAdapterRandom);
        recommendAdapterRandom.setOnItemClickListener((adapter, view1, position) ->{
            Toast.makeText(getActivity(), clothesListPersonal.get(position).clothesId+"3333333333333333",Toast.LENGTH_SHORT).show();
        });


        return view;
    }

    private void init(){

        Clothes clothes1 = new Clothes(R.drawable.test1, "brand1套装", "brand1", "S M L", "套装", "潮流风尚");
        Clothes clothes2 = new Clothes(R.drawable.test2, "brand2套装", "brand2", "M L XL", "连衣裙", "浪漫度假");
        Clothes clothes3 = new Clothes(R.drawable.test3, "brand3套装", "brand3", "XS S M", "半身裙", "美式休闲");
        Clothes clothes4 = new Clothes(R.drawable.test4, "brand4套装", "brand4", "S M", "毛衣/针织衫", "可爱减龄");
        Clothes clothes5 = new Clothes(R.drawable.test5, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");
        Clothes clothes6 = new Clothes(R.drawable.test3, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");
        Clothes clothes7 = new Clothes(R.drawable.test4, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");
        Clothes clothes8 = new Clothes(R.drawable.test2, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");
        Clothes clothes9 = new Clothes(R.drawable.test1, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");

        clothesListPersonal.add(clothes1);
        clothesListPersonal.add(clothes2);
        clothesListPersonal.add(clothes3);
        clothesListPersonal.add(clothes4);
        clothesListPersonal.add(clothes5);
        clothesListPersonal.add(clothes6);
        clothesListPersonal.add(clothes7);
        clothesListPersonal.add(clothes8);
        clothesListPersonal.add(clothes9);


        clothesListUpdate.add(clothes1);
        clothesListUpdate.add(clothes2);
        clothesListUpdate.add(clothes3);
        clothesListUpdate.add(clothes4);
        clothesListUpdate.add(clothes5);
        clothesListUpdate.add(clothes1);
        clothesListUpdate.add(clothes2);
        clothesListUpdate.add(clothes3);
        clothesListUpdate.add(clothes4);



        Brand brand1 = new Brand(1, R.drawable.brand1, "GINGKOROOM");
        Brand brand2 = new Brand(2, R.drawable.brand2, "HELLO SEPTEMBER");
        Brand brand3 = new Brand(3, R.drawable.brand3, "devil's bones");
        Brand brand4 = new Brand(4, R.drawable.brand4, "CAYLAR");
        Brand brand5 = new Brand(5, R.drawable.brand5, "FitGlasses");
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


    }

    @Override
    public void clickItem(int pos) {
        Toast.makeText(getActivity(), "点击了："+(pos), Toast.LENGTH_SHORT).show();
        recyclerCoverFlow_recommend_update.smoothScrollToPosition(pos);
    }
}
