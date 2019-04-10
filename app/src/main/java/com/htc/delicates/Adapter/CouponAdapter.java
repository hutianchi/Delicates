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
import com.htc.delicates.Model.Coupon;
import com.htc.delicates.R;

import java.text.DateFormat;
import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MyViewHolder>{

    private List<Coupon> couponList;

    private OnUseClickLister mOnUseClickListener;

    private Context context;

    public CouponAdapter(Context context, List<Coupon> coupons) {
        this.context = context;
        this.couponList = coupons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.coupon_item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (couponList != null){
            myViewHolder.itemView.setTag(i);
            View view = myViewHolder.useCoupon;
            view.setTag(i);
            if (!view.hasOnClickListeners()) {
                view.setOnClickListener(v -> {
                    if (mOnUseClickListener != null) {
                        mOnUseClickListener.onUseClick(v, (Integer) v.getTag());
                    }
                });
            }
            Coupon coupon = couponList.get(i);
            myViewHolder.count.setText("¥"+coupon.count);
            myViewHolder.details.setText(coupon.details);
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            myViewHolder.date.setText(dateFormat.format(coupon.date));
        }

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView count;

        TextView date;

        TextView details;

        TextView useCoupon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.coupon_count);
            date = itemView.findViewById(R.id.coupon_date);
            details = itemView.findViewById(R.id.coupon_details);
            useCoupon = itemView.findViewById(R.id.coupon_use);
        }
    }

    public void setOnUseClickListener(OnUseClickLister listener) {
        this.mOnUseClickListener = listener;
    }

    public interface OnUseClickLister {
        void onUseClick(View view, int position);
    }
}
