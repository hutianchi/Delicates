package com.htc.delicates.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Activity.ClothesDetailActivity;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;
import com.htc.delicates.Adapter.ClothesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClothesSelectFragment extends Fragment implements View.OnClickListener{


    private LinearLayout container1;
    private LinearLayout container2;
    private LinearLayout container3;
    private String sizes[] = new String[]{"XXS","XS","S","M","L","XL","XXL","均码"};
    private String types[] = new String[]{"套装","衬衫","上衣","卫衣","长裤","毛衣/针织衫","西服","大衣","羽绒服/棉服","夹克/外套","连体裤","风衣","t恤","中裤/短裤","背心/马甲"};
    private String tags[] = new String[]{"7日上新","可爱减龄","优雅职场","通勤造型","美式休闲","浪漫度假","潮流风尚","约会着装","high店着装"};
    private String envs[] = new String[]{"职场","生活","社交"};
    private ArrayList<String> sizeList = new ArrayList<>();
    private ArrayList<String> typeList = new ArrayList<>();
    private ArrayList<String> tagList = new ArrayList<>();
    private ArrayList<String> envList = new ArrayList<>();

    RecyclerView clothesRecyclerView;

    ClothesAdapter clothesAdapter;

    List<Clothes> clothesList = new ArrayList<>();

    DrawerLayout drawerLayout;

    RelativeLayout drawer_content;

    TextView btn_select;
    TextView btn_commit;

    GridView gv_size;
    GridView gv_type;
    GridView gv_env;
    GridView gv_tag;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_clothes, container, false);
        ImageView s1 = (ImageView) view.findViewById(R.id.s1);
        ImageView s2 = (ImageView) view.findViewById(R.id.s2);
        ImageView s3 = (ImageView) view.findViewById(R.id.s3);
        ImageView s4 = (ImageView) view.findViewById(R.id.s4);
        ImageView s5 = (ImageView) view.findViewById(R.id.s5);
        ImageView s6 = (ImageView) view.findViewById(R.id.s6);
        ImageView s7 = (ImageView) view.findViewById(R.id.s7);
        ImageView s8 = (ImageView) view.findViewById(R.id.s8);
        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        s3.setOnClickListener(this);
        s4.setOnClickListener(this);
        s5.setOnClickListener(this);
        s6.setOnClickListener(this);
        s7.setOnClickListener(this);
        s8.setOnClickListener(this);

        container1 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewItemContainer1);
        container2 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewItemContainer2);
        container3 = (LinearLayout) view.findViewById(R.id.horizontalScrollViewItemContainer3);

        Collections.addAll(sizeList, sizes);
        Collections.addAll(typeList, types);
        Collections.addAll(tagList, tags);
        Collections.addAll(envList, envs);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(45, 5, 30, 5);

        for (int i = 0; i < sizeList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
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
            container2.addView(textView);
            container2.invalidate();
        }

        for (int i = 0; i < tagList.size(); i++)
        {
            TextView textView = new TextView(getActivity());
            textView.setText(tagList.get(i));
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(14);
            final int finalI = i;
            textView.setOnClickListener(v -> {
                Toast.makeText(getActivity(),"this is " + tagList.get(finalI), Toast.LENGTH_SHORT).show();
            });
            textView.setLayoutParams(layoutParams);
            container3.addView(textView);
            container3.invalidate();
        }

        clothesRecyclerView = (RecyclerView) view.findViewById(R.id.clothes_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        clothesRecyclerView.setLayoutManager(layoutManager);
        clothesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        clothesAdapter = new ClothesAdapter(getActivity(), clothesList);
        clothesRecyclerView.setAdapter(clothesAdapter);
        clothesAdapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  订单详情页跳转
            //
            Intent intent = new Intent(getActivity(), ClothesDetailActivity.class);
            startActivity(intent);

            Toast.makeText(getActivity(),"this is clothes detail!!!     "+ clothesList.get(position).clothesName, Toast.LENGTH_SHORT).show();
        });

        btn_select = (TextView) view.findViewById(R.id.select_btn);
        btn_commit = (TextView) view.findViewById(R.id.select_commit);
        btn_select.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawer_content = (RelativeLayout) view.findViewById(R.id.drawer_content);
        gv_size = (GridView) view.findViewById(R.id.select_size);
        gv_type = (GridView) view.findViewById(R.id.select_type);
        gv_env = (GridView) view.findViewById(R.id.select_env);
        gv_tag = (GridView) view.findViewById(R.id.select_tag);

        gv_size.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.select_size_item, sizeList));
        gv_size.setOnItemClickListener((parent, view1, position, id) -> {
            TextView tv = (TextView) view1.findViewById(R.id.tv_select_size);
            Toast.makeText(getActivity(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
        });
        gv_type.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.select_type_item, typeList));
        gv_type.setOnItemClickListener((parent, view1, position, id) -> {
            TextView tv = (TextView)  view1.findViewById(R.id.tv_select_type);
            Toast.makeText(getActivity(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
        });
        gv_env.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.select_env_item, envList));
        gv_env.setOnItemClickListener((parent, view1, position, id) -> {
            TextView tv = (TextView)  view1.findViewById(R.id.tv_select_env);
            Toast.makeText(getActivity(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
        });
        gv_tag.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.select_tag_item, tagList));
        gv_tag.setOnItemClickListener((parent, view1, position, id) -> {
            TextView tv = (TextView)  view1.findViewById(R.id.tv_select_tag);
            Toast.makeText(getActivity(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s1:
                Toast.makeText(getContext(), "this is s1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s2:
                Toast.makeText(getContext(), "this is s2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s3:
                Toast.makeText(getContext(), "this is s3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s4:
                Toast.makeText(getContext(), "this is s4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s5:
                Toast.makeText(getContext(), "this is s5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s6:
                Toast.makeText(getContext(), "this is s6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s7:
                Toast.makeText(getContext(), "this is s7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.s8:
                Toast.makeText(getContext(), "this is s8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_btn:
                drawerLayout.openDrawer(drawer_content);
                break;
            case R.id.select_commit:
                drawerLayout.closeDrawers();
                break;
        }
    }
}
