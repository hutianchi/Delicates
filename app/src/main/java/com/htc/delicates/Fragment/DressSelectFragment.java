package com.htc.delicates.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Adapter.ClothesAdapter;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DressSelectFragment extends Fragment {

    private LinearLayout container1;
    private LinearLayout container2;
    private LinearLayout container3;
    private LinearLayout container4;
    private LinearLayout container5;

    private String sizes[] = new String[]{"XXS","XS","S","M","L","XL","XXL","均码"};
    private String types[] = new String[]{"迷你","膝上","及膝","膝下","齐地","拖地/鱼尾"};
    private String rants[] = new String[]{"< ¥200","¥200 - ¥300","¥300 - ¥500","> ¥500"};
    private String envs[] = new String[]{"酒会/年会","派对","婚礼","晚宴"};
    private ArrayList<String> sizeList = new ArrayList<>();
    private ArrayList<String> typeList = new ArrayList<>();
    private ArrayList<String> rentList = new ArrayList<>();
    private ArrayList<String> envList = new ArrayList<>();

    RecyclerView clothesRecyclerView;

    ClothesAdapter clothesAdapter;

    List<Clothes> dressList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_dress, container, false);

        container1 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewDressItem1);
        container2 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewDressItem2);
        container3 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewDressItem3);
        container4 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewDressItem4);

        Collections.addAll(sizeList, sizes);
        Collections.addAll(typeList, types);
        Collections.addAll(envList, envs);
        Collections.addAll(rentList, rants);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(30, 5, 30, 5);


        for (int i = 0; i < sizeList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
            //textView.setId();
            textView.setText(sizeList.get(i));
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(14);
            final int finalI = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getActivity(),"this is " + sizeList.get(finalI), Toast.LENGTH_SHORT).show();
            });
            textView.setLayoutParams(layoutParams);
            container1.addView(textView);
            container1.invalidate();
        }

        for (int i = 0; i < envList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
            textView.setText(envList.get(i));
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(14);
            final int finalI = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getActivity(),"this is " + envList.get(finalI), Toast.LENGTH_SHORT).show();
            });
            textView.setLayoutParams(layoutParams);
            container2.addView(textView);
            container2.invalidate();
        }

        for (int i = 0; i < typeList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
            textView.setText(typeList.get(i));
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(14);
            final int finalI = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getActivity(),"this is " + typeList.get(finalI), Toast.LENGTH_SHORT).show();
            });
            textView.setLayoutParams(layoutParams);
            container3.addView(textView);
            container3.invalidate();
        }

        for (int i = 0; i < rentList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
            textView.setText(rentList.get(i));
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(14);
            final int finalI = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getActivity(),"this is " + rentList.get(finalI), Toast.LENGTH_SHORT).show();
            });
            textView.setLayoutParams(layoutParams);
            container4.addView(textView);
            container4.invalidate();
        }

        clothesRecyclerView = (RecyclerView) view.findViewById(R.id.dress_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        clothesRecyclerView.setLayoutManager(layoutManager);
        clothesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        clothesAdapter = new ClothesAdapter(getActivity(), dressList);
        clothesRecyclerView.setAdapter(clothesAdapter);
        clothesAdapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  订单详情页跳转
            //

            Toast.makeText(getActivity(),"this is dress detail!!!     "+ dressList.get(position).clothesName, Toast.LENGTH_SHORT).show();
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

            dressList.add(clothes1);
            dressList.add(clothes2);
            dressList.add(clothes3);
            dressList.add(clothes4);
            dressList.add(clothes5);
        }
    }


}
