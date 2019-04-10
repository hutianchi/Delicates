package com.htc.delicates.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htc.delicates.Model.Clothes;
import com.htc.delicates.R;

import java.util.List;

public class WardrobeAdapter extends RecyclerView.Adapter<WardrobeAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Clothes> clothesList;

    private Context mContext;

    private OnItemClickListener mListener;

    private OnDeleteClickLister mDeleteClickListener;

    private OnSizeClickListener mSizeClickListener;

    public WardrobeAdapter(List<Clothes> clothesList, Context mContext) {
        this.clothesList = clothesList;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wardrobe_item, viewGroup, false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (clothesList != null) {
            myViewHolder.itemView.setTag(i);
            View view = myViewHolder.tv_delete;
            view.setTag(i);
            if (!view.hasOnClickListeners()) {
                view.setOnClickListener(v -> {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                });
            }
            View view1 = myViewHolder.tv_clothesSize;
            view1.setTag(i);
            if (!view1.hasOnClickListeners()) {
                view1.setOnClickListener(v -> {
                    if (mSizeClickListener != null) {
                        mSizeClickListener.onSizeClick(v, (Integer) v.getTag());
                    }
                });
            }
            Clothes clothes = clothesList.get(i);
            myViewHolder.im_clothes.setImageResource(clothes.clothesId);
            myViewHolder.tv_clothes.setText(clothes.clothesName+"\n"+clothes.clothesBrand);
            myViewHolder.tv_clothesSize.setText("已选:"+clothes.clothesSize);
        }
    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView im_clothes;
        TextView tv_clothes;
        TextView tv_delete;
        TextView tv_clothesSize;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_clothes = (ImageView) itemView.findViewById(R.id.clothes_im);
            tv_clothes = (TextView) itemView.findViewById(R.id.clothes_tv);
            tv_clothesSize = (TextView) itemView.findViewById(R.id.clothes_size_tv);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);

        }

    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }

    public void setOnSizeClickListener(OnSizeClickListener listener){
        this.mSizeClickListener = listener;
    }

    public interface  OnSizeClickListener{
        void onSizeClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        //item点击回调
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);

    }
}
