package com.htc.delicates.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.htc.delicates.Model.Order;
import com.htc.delicates.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> implements View.OnClickListener{

    private List<Order> orderList;

    private Context mContext;

    private OnItemClickListener mListener;

    private OnDeleteClickLister mDeleteClickListener;

    public OrderAdapter(Context mContext, List<Order> mList){
        this.mContext = mContext;
        this.orderList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item, viewGroup, false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (orderList != null) {
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
            Order order = orderList.get(i);
            myViewHolder.im_order.setImageResource(order.imageId);
            myViewHolder.tv_order.setText(order.orderName);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView im_order;
        TextView tv_order;
        TextView tv_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_order = (ImageView) itemView.findViewById(R.id.order_image);
            tv_order = (TextView) itemView.findViewById(R.id.order_text);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        }

    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        //item点击回调
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);

    }
}
