package com.htc.delicates.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.htc.delicates.Model.Brand;
import com.htc.delicates.MyView.MySidebar;
import com.htc.delicates.R;

import java.util.Arrays;
import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.MyViewHolder> implements SectionIndexer, View.OnClickListener{

    private List<Brand> brandList;

    private OnItemClickListener mListener;

    private Context context;

    public BrandListAdapter(List<Brand> brandList, Context context) {
        this.brandList = brandList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_brand_item, viewGroup,false);
        view.setOnClickListener(this);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Brand brand;
        if (brandList != null){
            myViewHolder.itemView.setTag(i);
            brand = brandList.get(i);
            if (i == 0){
                myViewHolder.brandHeader.setVisibility(View.VISIBLE);
            }else if (brandList.get(i).getFirstAlphabet().charAt(0) != brandList.get(i - 1).getFirstAlphabet().charAt(0)){
                myViewHolder.brandHeader.setVisibility(View.VISIBLE);
            }else {
                myViewHolder.brandHeader.setVisibility(View.GONE);
            }
            myViewHolder.brandHeader.setText(brandList.get(i).getFirstAlphabet().toUpperCase());
            myViewHolder.brandName.setText(brand.brandName);
            myViewHolder.isWish.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Log.e("zbv","isChecked="+isChecked+";position="+i);
                brandList.get(i).setSelected(isChecked);
            });

        }
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public Object[] getSections() {
        return Arrays.copyOf(MySidebar.alphabets,MySidebar.alphabets.length);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i=0; i<getItemCount(); i++){
            if (((String)getSections()[sectionIndex]).charAt(0) == brandList.get(i).getFirstAlphabet().toUpperCase().charAt(0)){
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    public interface OnItemClickListener {
        //item点击回调
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView brandName;

        private TextView brandHeader;

        private CheckBox isWish;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brandHeader = (TextView) itemView.findViewById(R.id.brand_header);
            brandName = (TextView) itemView.findViewById(R.id.brand_name);
            isWish = (CheckBox) itemView.findViewById(R.id.isWish);
        }
    }

}
