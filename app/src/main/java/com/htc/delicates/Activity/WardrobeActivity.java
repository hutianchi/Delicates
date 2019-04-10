package com.htc.delicates.Activity;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.Adapter.OrderAdapter;
import com.htc.delicates.Adapter.WardrobeAdapter;
import com.htc.delicates.Fragment.BaseFragment;
import com.htc.delicates.Model.Clothes;
import com.htc.delicates.MyView.SlideRecyclerView;
import com.htc.delicates.R;
import com.htc.delicates.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WardrobeActivity extends AppCompatActivity {

    SlideRecyclerView wardrobeRecycler;

    WardrobeAdapter adapter;

    List<Clothes> clothesList = new ArrayList<>();

    Toolbar mToolbar_wardrobe;

    TextView sendClothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        mToolbar_wardrobe = (Toolbar) findViewById(R.id.toolbar_wardrobe);
        setSupportActionBar(mToolbar_wardrobe);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar_wardrobe.setNavigationOnClickListener(v -> onBackPressed());
        sendClothes = (TextView) findViewById(R.id.tv_send);
        sendClothes.setOnClickListener(v -> {
            Toast.makeText(this, "寄送成功",Toast.LENGTH_SHORT).show();
        });
        wardrobeRecycler = (SlideRecyclerView)findViewById(R.id.wardrobe_recycler);
        wardrobeRecycler.setLayoutManager(new LinearLayoutManager(this));
        wardrobeRecycler.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.my_divider));
        wardrobeRecycler.addItemDecoration(divider);
        init();
        adapter = new WardrobeAdapter( clothesList, this);
        wardrobeRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  订单详情页跳转
            //

            Toast.makeText(this,"this is order detail!!!     "+ clothesList.get(position).clothesName, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnDeleteClickListener((view1, position) -> {
            clothesList.remove(position);
            adapter.notifyDataSetChanged();
            wardrobeRecycler.closeMenu();
        });
        adapter.setOnSizeClickListener(((view, position) -> {
            initDialog(clothesList.get(position).clothesId, clothesList.get(position).clothesName, clothesList.get(position).clothesBrand, clothesList.get(position).clothesSize);
            Toast.makeText(this, "更换尺码",Toast.LENGTH_SHORT).show();
        }));
    }

    private void init(){
        Clothes clothes1 = new Clothes(R.drawable.test1, "brand1套装", "brand1", "M", "套装", "潮流风尚");
        Clothes clothes2 = new Clothes(R.drawable.test2, "brand2套装", "brand2", "L", "连衣裙", "浪漫度假");
        Clothes clothes3 = new Clothes(R.drawable.test3, "brand3套装", "brand3", "XS", "半身裙", "美式休闲");

        clothesList.add(clothes1);
        clothesList.add(clothes2);
        clothesList.add(clothes3);
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

}
