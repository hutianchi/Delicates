package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Adapter.ClothesAdapter;
import com.htc.delicates.Model.Brand;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class BrandDetailActivity extends AppCompatActivity {

    Toolbar toolbar_brand_detail;

    RecyclerView clothesRecyclerView;

    ClothesAdapter clothesAdapter;

    Brand brand = new Brand(1, R.drawable.brand1, "GINGKOROOM");

    List<Clothes> clothesList = new ArrayList<>();

    TextView brandName1;
    TextView brandName2;

    ImageView brandImage;

    TextView brandText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);

        init();

        toolbar_brand_detail = (Toolbar) findViewById(R.id.toolbar_brand_detail);
        setSupportActionBar(toolbar_brand_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_brand_detail.setNavigationOnClickListener(v -> onBackPressed());

        brandImage = (ImageView) findViewById(R.id.brand_detail_image);
        brandImage.setImageResource(R.drawable.branddetail);
        brandImage.setScaleType(ImageView.ScaleType.FIT_XY);//铺满屏幕

        brandName1 = (TextView) findViewById(R.id.brand_detail_name);
        brandName2 = (TextView) findViewById(R.id.brand_detail_name1);
        brandName1.setText(brand.brandName);
        brandName2.setText(brand.brandName);

        brandText = (TextView) findViewById(R.id.brand_detail_text);
        brandText.setText("第八十九啊是抱佛脚还是达不到空间吧就看不点击啊是卡夫卡司法局还是把咖啡阿宝开发建设步伐撒把咖啡倒苦水把党风建设");

        clothesRecyclerView = (RecyclerView) findViewById(R.id.brand_detail_clothes);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        clothesRecyclerView.setLayoutManager(layoutManager);
        clothesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        clothesAdapter = new ClothesAdapter(this, clothesList);
        clothesRecyclerView.setAdapter(clothesAdapter);
        clothesAdapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  订单详情页跳转
            //

            Toast.makeText(this,"this is clothes detail!!!     "+ clothesList.get(position).clothesName, Toast.LENGTH_SHORT).show();
        });
    }

    private void init(){

        for (int i=0;i<2;i++){
            Clothes clothes1 = new Clothes(R.drawable.test1, "brand1套装", "GINGKOROOM", "S M L", "套装", "潮流风尚");
            Clothes clothes2 = new Clothes(R.drawable.test2, "brand2套装", "GINGKOROOM", "M L XL", "连衣裙", "浪漫度假");
            Clothes clothes3 = new Clothes(R.drawable.test3, "brand3套装", "GINGKOROOM", "XS S M", "半身裙", "美式休闲");
            Clothes clothes4 = new Clothes(R.drawable.test4, "brand4套装", "GINGKOROOM", "S M", "毛衣/针织衫", "可爱减龄");
            Clothes clothes5 = new Clothes(R.drawable.test5, "brand5套装", "GINGKOROOM", "S M L", "上衣", "潮流风尚");

            clothesList.add(clothes1);
            clothesList.add(clothes2);
            clothesList.add(clothes3);
            clothesList.add(clothes4);
            clothesList.add(clothes5);
        }
    }
}
