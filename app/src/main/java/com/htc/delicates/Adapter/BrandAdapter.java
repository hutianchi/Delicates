package com.htc.delicates.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htc.delicates.Model.Brand;
import com.htc.delicates.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> implements View.OnClickListener{

    private List<Brand> brandList;

    private OnItemClickListener mListener;

    private Context context;

    private int itemResourse;

    public BrandAdapter(Context context, List<Brand> brandList, int itemResourse){
        this.brandList = brandList;
        this.context = context;
        this.itemResourse = itemResourse;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(itemResourse, viewGroup,false);
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
            myViewHolder.im_brand.setImageResource(brand.brandImage);
            myViewHolder.tv_brand.setText(brand.brandName);
        }
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView im_brand;

        TextView tv_brand;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_brand = (ImageView) itemView.findViewById(R.id.recommend_image);
            tv_brand = (TextView) itemView.findViewById(R.id.brand_text);
        }
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

    public interface OnItemClickListener {
        //item点击回调
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);

    }
}
