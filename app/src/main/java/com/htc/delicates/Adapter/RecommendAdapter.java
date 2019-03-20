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

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> implements View.OnClickListener{

    private List<Clothes> clothesList;

    private OnItemClickListener mListener;

    private Context context;

    public RecommendAdapter(Context context, List<Clothes> clothesList) {
        this.clothesList = clothesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecommendAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_item, viewGroup,false);
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
            myViewHolder.im_recommend.setImageResource(clothes.clothesId);
            myViewHolder.tv_recommend.setText(clothes.clothesName);
        }
    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView im_recommend;
        TextView tv_recommend;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_recommend = (ImageView) itemView.findViewById(R.id.recommend_image);
            tv_recommend = (TextView) itemView.findViewById(R.id.recommend_text);
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
