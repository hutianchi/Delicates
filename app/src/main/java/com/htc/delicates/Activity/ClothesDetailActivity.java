package com.htc.delicates.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Adapter.ClothesAdapter;
import com.htc.delicates.Model.Brand;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClothesDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,ViewPager.OnTouchListener{

    private static final String TAG = "ClothesDetailActivity";

    Toolbar toolbar_clothes_detail;
    TextView toolbarBrand;
    TextView toolbarClothes;

    ViewPager viewPager;

    ViewPagerAdapter adapter;

    RecyclerView clothesRecyclerView;

    ClothesAdapter clothesAdapter;

    List<Clothes> clothesList = new ArrayList<>();

    Integer images[] = new Integer[]{R.drawable.test1,R.drawable.test2,R.drawable.test3,R.drawable.test4,R.drawable.test5};

    Clothes clothes = new Clothes(R.drawable.test1, "brand1套装", "brand1", "S    M    L", "套装", "潮流风尚");

    List<Integer> imageList = new ArrayList<>();

    TextView pages;

    RelativeLayout relativeLayout;

    TextView clothesWish;
    TextView addClothes;
    TextView brandWish;
    TextView clothesBrand;
    TextView clothesName;
    TextView clothesSize;
    TextView brandName;
    CircleImageView brandImage;

    TextView detailPrice;
    TextView detailType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_detail);

        toolbar_clothes_detail = (Toolbar) findViewById(R.id.toolbar_clothes_detail);
        setSupportActionBar(toolbar_clothes_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_clothes_detail.setNavigationOnClickListener(v -> onBackPressed());
        toolbarBrand = (TextView) findViewById(R.id.toolbar_brand_name);
        toolbarClothes = (TextView) findViewById(R.id.toolbar_clothes_name);
        toolbarClothes.setText(clothes.clothesName);
        toolbarBrand.setText(clothes.clothesBrand);

        Collections.addAll(imageList,images);

        pages = (TextView) findViewById(R.id.page);
        pages.setText("1/"+imageList.size());


        detailPrice = (TextView) findViewById(R.id.detail_price);
        detailType = (TextView) findViewById(R.id.detail_type);
        detailPrice.setText("¥599");
        detailType.setText("衬衫");

        clothesWish = (TextView) findViewById(R.id.add_clothes_wish);
        addClothes = (TextView) findViewById(R.id.add_clothes);
        brandWish = (TextView) findViewById(R.id.add_brand_wish);
        clothesBrand = (TextView) findViewById(R.id.clothes_detail_brand);
        clothesName = (TextView) findViewById(R.id.clothes_detail_name);
        clothesSize = (TextView) findViewById(R.id.clothes_detail_size);
        brandName = (TextView) findViewById(R.id.brand_name1);
        brandImage = (CircleImageView) findViewById(R.id.brand_image);
        relativeLayout = (RelativeLayout) findViewById(R.id.enter_brand);

        clothesBrand.setText(clothes.clothesBrand);
        clothesName.setText(clothes.clothesName);
        clothesSize.setText(clothes.clothesSize);

        Brand brand = new Brand(1, R.drawable.branddetail, "GINGKOROOM");

        brandName.setText(brand.brandName);
        brandImage.setImageResource(brand.brandImage);
        brandWish.setOnClickListener(v -> {
            brandWish.setText("已关注");
            brandWish.setTextColor(Color.parseColor("#b0b0b0"));
            brandWish.setBackgroundResource(R.drawable.gray_shape);
            Toast.makeText(this, "已收藏",Toast.LENGTH_SHORT).show();
        });
        relativeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, BrandDetailActivity.class);
            startActivity(intent);
        });

        viewPager = (ViewPager) findViewById(R.id.clothes_image_list);
        adapter = new ViewPagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);


        init();
        clothesRecyclerView = (RecyclerView) findViewById(R.id.clothes_detail_clothes);
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

        clothesWish.setOnClickListener(v -> {
            Drawable drawableTop = getResources().getDrawable(R.drawable.collectioned);
            clothesWish.setCompoundDrawablesWithIntrinsicBounds(null,
                    drawableTop, null, null);
            clothesWish.setText("已收藏");
            Toast.makeText(this, "已收藏",Toast.LENGTH_SHORT).show();
        });
        addClothes.setOnClickListener(v -> {
            initDialog(clothes.clothesId, clothes.clothesName, clothes.clothesBrand, clothes.clothesSize);
        });
    }

    private void init(){
        for (int i=0;i<2;i++){
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


    private void initDialog(int resource, String name, String brand, String size) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_wardrobe_sizechange);
        ImageView dialogImage = (ImageView) bottomSheetDialog.findViewById(R.id.dialog_image);
        dialogImage.setImageResource(resource);
        TextView dialogName = (TextView) bottomSheetDialog.findViewById(R.id.dialog_name);
        dialogName.setText(name);
        TextView dialogBrand = (TextView) bottomSheetDialog.findViewById(R.id.dialog_brand);
        dialogBrand.setText(brand);
        TextView dialogSave = (TextView) bottomSheetDialog.findViewById(R.id.dialog_save);
        dialogSave.setText("加入衣柜");
        dialogSave.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        TextView size1 = (TextView) bottomSheetDialog.findViewById(R.id.size1);
        TextView size2 = (TextView) bottomSheetDialog.findViewById(R.id.size2);
        TextView size3 = (TextView) bottomSheetDialog.findViewById(R.id.size3);
        size1.setEnabled(true);
        size2.setEnabled(true);
        size3.setEnabled(true);
        size1.setOnClickListener( v -> {
            size1.setEnabled(false);
            size2.setEnabled(true);
            size3.setEnabled(true);
        });
        size2.setOnClickListener( v -> {
            size2.setEnabled(false);
            size1.setEnabled(true);
            size3.setEnabled(true);
        });
        size3.setOnClickListener( v -> {
            size3.setEnabled(false);
            size1.setEnabled(true);
            size2.setEnabled(true);
        });
        bottomSheetDialog.show();
    }



    @Override
    public void onPageScrolled(int i, float v, int i1) {}

    @Override
    public void onPageSelected(int i) {
        pages.setText(++i+"/"+imageList.size());
    }

    @Override
    public void onPageScrollStateChanged(int i) {}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    public class ViewPagerAdapter extends PagerAdapter {

        List<Integer> imageLists;

        List<ImageView> imageViewList = new ArrayList<>();

        Context context;

        public ViewPagerAdapter(Context context, List<Integer> lists)
        {
            this.context = context;
            imageLists = lists;
            for (int i=0;i<imageLists.size();i++){
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(imageLists.get(i));
                imageViewList.add(imageView);
            }
        }

        @Override
        public int getCount() {                                                                 //获得size
            // TODO Auto-generated method stub
            return imageLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int position, Object object)                       //销毁Item
        {
            ImageView imageView=imageViewList.get(position);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            viewGroup.removeView(imageView);
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position)                               //实例化Item
        {
            ImageView imageView = imageViewList.get(position);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//铺满屏幕
            viewGroup.addView(imageView);
            return imageView;
        }
    }
}
