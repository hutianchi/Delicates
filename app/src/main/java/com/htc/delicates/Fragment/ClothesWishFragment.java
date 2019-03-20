package com.htc.delicates.Fragment;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.htc.delicates.Adapter.ClothesAdapter;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class ClothesWishFragment extends Fragment {

    LinearLayout lay1,lay2;

    Button selectBtn;

    RecyclerView clothesRecyclerView;

    ClothesAdapter clothesAdapter;

    List<Clothes> clothesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_clothes, container, false);
        lay1 = (LinearLayout) view.findViewById(R.id.clothes_wish_lay1);
        lay2 = (LinearLayout) view.findViewById(R.id.clothes_wish_lay2);
        selectBtn = (Button) view.findViewById(R.id.wish_select_btn);
        selectBtn.setOnClickListener( v ->{
            lay2.setVisibility(View.GONE);
            lay1.setVisibility(View.VISIBLE);
        });
        clothesRecyclerView = (RecyclerView) view.findViewById(R.id.clothes_wish);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        clothesRecyclerView.setLayoutManager(layoutManager);
        clothesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        clothesAdapter = new ClothesAdapter(getActivity(), clothesList);
        clothesRecyclerView.setAdapter(clothesAdapter);
        clothesAdapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  衣物详情页跳转
            //

            Toast.makeText(getActivity(),"this is clothes detail!!!     "+ clothesList.get(position).clothesName, Toast.LENGTH_SHORT).show();
        });




        return view;
    }

    private void init(){
        for (int i=0;i<3;i++){
            Clothes clothes1 = new Clothes(R.drawable.test1, "brand1套装", "brand1", "S M L", "套装", "潮流风尚");
            Clothes clothes2 = new Clothes(R.drawable.test2, "brand2套装", "brand2", "M L XL", "连衣裙", "浪漫度假");
            Clothes clothes3 = new Clothes(R.drawable.test3, "brand3套装", "brand3", "XS S M", "半身裙", "美式休闲");
            Clothes clothes4 = new Clothes(R.drawable.test4, "brand4套装", "brand4", "S M", "毛衣/针织衫", "可爱减龄");
            Clothes clothes5 = new Clothes(R.drawable.test5, "brand5套装", "brand5", "S M L", "上衣", "潮流风尚");

            clothesList.add(clothes1);
            clothesList.add(clothes2);
            clothesList.add(clothes3);
            clothesList.add(clothes4);
            clothesList.add(clothes5);
        }
    }
}
