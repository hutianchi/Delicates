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

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.MyViewHolder> implements View.OnClickListener{

    private List<Clothes> clothesList;

    private OnItemClickListener mListener;

    private Context context;

    public ClothesAdapter(Context context, List<Clothes> clothesList) {
        this.clothesList = clothesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.clothes_item, viewGroup,false);
        view.setOnClickListener(this);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Clothes clothes;
        if (clothesList != null){
            myViewHolder.itemView.setTag(i);
            clothes = clothesList.get(i);
            myViewHolder.im_clothes.setImageResource(clothes.clothesId);
            myViewHolder.tv_clothes.setText(clothes.clothesBrand+"\n"+clothes.clothesType+"\n"+clothes.clothesSize);
        }
    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView im_clothes;
        TextView tv_clothes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_clothes = (ImageView) itemView.findViewById(R.id.clothes_image);
            tv_clothes = (TextView) itemView.findViewById(R.id.clothes_text);
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
