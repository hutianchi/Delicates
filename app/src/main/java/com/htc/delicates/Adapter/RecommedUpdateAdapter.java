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

public class RecommedUpdateAdapter extends RecyclerView.Adapter<RecommedUpdateAdapter.MyViewHolder> {

    private Context context;

    private onItemClick clickCb;

    private List<Clothes> clothes;

    public RecommedUpdateAdapter(Context c) {
        context = c;
    }

    public RecommedUpdateAdapter(Context c, onItemClick cb, List<Clothes> clothesArrayList) {
        context = c;
        clickCb = cb;
        clothes = clothesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_update_item, viewGroup,false);
        RecommedUpdateAdapter.MyViewHolder myViewHolder = new RecommedUpdateAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Clothes cloth;
        if (clothes != null){
            myViewHolder.itemView.setTag(i);
            cloth = clothes.get(i);
            myViewHolder.im_recommend_update.setImageResource(cloth.clothesId);
            myViewHolder.tv_recommend_update.setText(cloth.clothesName);
        }
        myViewHolder.itemView.setOnClickListener(v -> {
            if (clickCb != null) {
                clickCb.clickItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clothes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView im_recommend_update;
        TextView tv_recommend_update;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_recommend_update = (ImageView) itemView.findViewById(R.id.recommend_image_update);
            tv_recommend_update = (TextView) itemView.findViewById(R.id.recommend_text_update);
        }
    }

    public interface onItemClick {
        void clickItem(int pos);
    }
}
